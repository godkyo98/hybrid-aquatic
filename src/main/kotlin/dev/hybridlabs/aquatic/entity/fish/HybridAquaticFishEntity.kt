package dev.hybridlabs.aquatic.entity.fish

import dev.hybridlabs.aquatic.entity.fish.HybridAquaticFishEntity.VariantCollisionRules.ExclusionStatus.EXCLUSIVE
import dev.hybridlabs.aquatic.entity.fish.HybridAquaticFishEntity.VariantCollisionRules.ExclusionStatus.INCLUSIVE
import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.block.Blocks
import net.minecraft.entity.*
import net.minecraft.entity.ai.control.MoveControl
import net.minecraft.entity.ai.goal.*
import net.minecraft.entity.ai.pathing.EntityNavigation
import net.minecraft.entity.ai.pathing.SwimNavigation
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.data.DataTracker
import net.minecraft.entity.data.TrackedData
import net.minecraft.entity.data.TrackedDataHandlerRegistry
import net.minecraft.entity.mob.WaterCreatureEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.nbt.NbtCompound
import net.minecraft.particle.ParticleTypes
import net.minecraft.registry.tag.FluidTags
import net.minecraft.registry.tag.TagKey
import net.minecraft.sound.SoundEvent
import net.minecraft.sound.SoundEvents
import net.minecraft.util.Identifier
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.MathHelper
import net.minecraft.util.math.Vec3d
import net.minecraft.util.math.random.Random
import net.minecraft.world.LocalDifficulty
import net.minecraft.world.ServerWorldAccess
import net.minecraft.world.World
import net.minecraft.world.WorldAccess
import net.minecraft.world.biome.Biome
import software.bernie.geckolib.animatable.GeoEntity
import software.bernie.geckolib.core.animatable.GeoAnimatable
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache
import software.bernie.geckolib.core.animation.*
import software.bernie.geckolib.core.animation.AnimationState
import software.bernie.geckolib.core.`object`.PlayState
import software.bernie.geckolib.util.GeckoLibUtil
import kotlin.math.sqrt

@Suppress("LeakingThis")
open class HybridAquaticFishEntity(
    type: EntityType<out HybridAquaticFishEntity>,
    world: World,
    private val variants: Map<String, FishVariant> = mutableMapOf(),
    open val prey: TagKey<EntityType<*>>,
    open val predator: TagKey<EntityType<*>>,
    open val assumeDefault: Boolean = true,
    open val collisionRules: List<VariantCollisionRules> = listOf()
) : WaterCreatureEntity(type, world), GeoEntity {

    private val factory = GeckoLibUtil.createInstanceCache(this)

    override fun initGoals() {
        super.initGoals()
        goalSelector.add(1, SwimToRandomPlaceGoal(this))
        goalSelector.add(2, SwimAroundGoal(this, 0.50, 6))
        goalSelector.add(1, EscapeDangerGoal(this, 1.25))
        goalSelector.add(2, FleeEntityGoal(this, LivingEntity::class.java, 8.0f, 1.2, 1.0) { !fromFishingNet && it.type.isIn(predator) })
        goalSelector.add(2, FleeEntityGoal(this, PlayerEntity::class.java, 5.0f, 1.0, 1.0) { !fromFishingNet })
        goalSelector.add(7, AttackGoal(this))
        targetSelector.add(6, ActiveTargetGoal(this, LivingEntity::class.java, 10, true, true) { hunger <= 300 && it.type.isIn(prey) })
    }

    override fun initDataTracker() {
        super.initDataTracker()
        dataTracker.startTracking(MOISTNESS, getMaxMoistness())
        dataTracker.startTracking(FISH_SIZE, 0)
        dataTracker.startTracking(ATTEMPT_ATTACK, false)
        dataTracker.startTracking(HUNGER, MAX_HUNGER)
        dataTracker.startTracking(VARIANT, "")
        dataTracker.startTracking(VARIANT_DATA, NbtCompound())
    }

    override fun initialize(
        world: ServerWorldAccess,
        difficulty: LocalDifficulty,
        spawnReason: SpawnReason,
        entityData: EntityData?,
        entityNbt: NbtCompound?
    ): EntityData? {
        this.air = getMaxMoistness()

        if(spawnReason == SpawnReason.SPAWN_EGG) {
            variantKey = variants.keys.elementAt(random.nextBetween(0, variants.size - 1))
        } else {
            // Handle collisions
            val validKeys = variants.filter { it.value.spawnCondition(world, spawnReason, blockPos, random) }.map { it.key }

            if(collisionRules.isNotEmpty()) {
                for (rule in collisionRules) {
                    val variantSet = rule.variants.toSet()
                    if ((rule.exclusionStatus == EXCLUSIVE && validKeys.toSet() == variantSet) || (rule.exclusionStatus == INCLUSIVE && validKeys.containsAll(variantSet))) {
                        variantKey = rule.collisionHandler(validKeys.toSet(), random, world)
                        break
                    }
                }
            } else {
                // Default to a priority based system
                val maxPriority = variants.values.maxOf { it.priority }
                val filteredMap = variants.filter { it.value.priority == maxPriority }

                filteredMap.keys.random()
            }

        }

        this.size = this.random.nextBetween(getMinSize(), getMaxSize())
        return super.initialize(world, difficulty, spawnReason, entityData, entityNbt)
    }

    override fun tick() {
        super.tick()
        if (isAiDisabled) {
            return
        }

        if (isWet) {
            moistness = getMaxMoistness()
        } else {
            moistness -= 1
            if (moistness <= -20) {
                moistness = 0
                damage(this.damageSources.dryOut(), 1.0f)
            }
        }
        if (world.isClient && isTouchingWater && isAttacking) {
            val rotationVec = getRotationVec(0.0f)
            val offsetY = 0.0f - random.nextFloat()

            for (i in 0..1) {
                val particleX = x - rotationVec.x * 0.1
                val particleY = y - rotationVec.y * -0.1
                val particleZ = z - rotationVec.z * offsetY

                world.addParticle(
                    ParticleTypes.DOLPHIN,
                    particleX,
                    particleY,
                    particleZ,
                    0.0,
                    0.0,
                    0.0
                )
            }
        }
    }

    override fun getLootTableId(): Identifier {
        return if (variant != null) {
            super.getLootTableId().withPath { path -> "${path}_${variant!!.variantName}" }
        } else {
            super.getLootTableId()
        }
    }

    private fun getHungerValue(entityType: EntityType<*>): Int {
        if (entityType.isIn(HybridAquaticEntityTags.CRAB))
            return 150
        if (entityType.isIn(HybridAquaticEntityTags.JELLYFISH))
            return 150
        if (entityType.isIn(HybridAquaticEntityTags.SMALL_PREY))
            return 300
        else if (entityType.isIn(HybridAquaticEntityTags.MEDIUM_PREY))
            return 600
        else if (entityType.isIn(HybridAquaticEntityTags.LARGE_PREY))
            return 1200

        return 0
    }

    open fun eatFish(entityType: EntityType<*>) {
        hunger += getHungerValue(entityType)
    }

    override fun tickWaterBreathingAir(air: Int) {}

    private fun getMaxMoistness(): Int {
        return 600
    }

    override fun writeCustomDataToNbt(nbt: NbtCompound) {
        super.writeCustomDataToNbt(nbt)
        nbt.putInt(MOISTNESS_KEY, moistness)
        nbt.putString(VARIANT_KEY, variantKey)
        nbt.put(VARIANT_DATA_KEY, variantData)
        nbt.putInt(FISH_SIZE_KEY, size)
        nbt.putInt(HUNGER_KEY, hunger)
        nbt.putBoolean("FromFishingNet", fromFishingNet)
    }

    open fun shouldFlopOnLand(): Boolean {
        return true
    }

    private var fromFishingNet = false

    override fun readCustomDataFromNbt(nbt: NbtCompound) {
        super.readCustomDataFromNbt(nbt)
        moistness = nbt.getInt(MOISTNESS_KEY)
        variantKey = nbt.getString(VARIANT_KEY)
        variantData = nbt.getCompound(VARIANT_DATA_KEY)
        size = nbt.getInt(FISH_SIZE_KEY)
        hunger = nbt.getInt(HUNGER_KEY)
        fromFishingNet = nbt.getBoolean("FromFishingNet")
    }

    open fun <E : GeoAnimatable> predicate(event: AnimationState<E>): PlayState {
        if (isSubmergedInWater) {
            event.controller.setAnimation(SWIM_ANIMATION)
            return PlayState.CONTINUE

        } else if (!isSubmergedInWater) {
            event.controller.setAnimation(FLOP_ANIMATION)
            return PlayState.CONTINUE
        }

        if (isWet && isFallFlying) {
            event.controller.setAnimation(SWIM_ANIMATION)
            return PlayState.CONTINUE
        }
        return PlayState.STOP
    }

    override fun getActiveEyeHeight(pose: EntityPose, dimensions: EntityDimensions): Float {
        return dimensions.height * 0.65f
    }

    override fun canImmediatelyDespawn(distanceSquared: Double): Boolean {
        return !hasCustomName()
    }

    override fun getLimitPerChunk(): Int {
        return 4
    }

    open val flopSound: SoundEvent = SoundEvents.ENTITY_PUFFER_FISH_FLOP

    override fun getHurtSound(source: DamageSource): SoundEvent {
        return SoundEvents.ENTITY_COD_HURT
    }

    override fun getDeathSound(): SoundEvent {
        return SoundEvents.ENTITY_COD_DEATH
    }

    override fun getAmbientSound(): SoundEvent {
        return SoundEvents.ENTITY_SALMON_AMBIENT
    }

    override fun getSplashSound(): SoundEvent {
        return SoundEvents.ENTITY_DOLPHIN_SPLASH
    }

    override fun createNavigation(world: World): EntityNavigation {
        return SwimNavigation(this, world)
    }

    // region Properties

    private var moistness: Int
        get() = dataTracker.get(MOISTNESS)
        set(moistness) {
            dataTracker.set(MOISTNESS, moistness)
        }

    var size: Int
        get() = dataTracker.get(FISH_SIZE)
        set(size) {
            dataTracker.set(FISH_SIZE, size)
        }

    private var hunger: Int
        get() = dataTracker.get(HUNGER)
        set(hunger) {
            dataTracker.set(HUNGER, hunger)
        }

    private var attemptAttack: Boolean
        get() = dataTracker.get(ATTEMPT_ATTACK)
        set(attemptAttack) {
            dataTracker.set(ATTEMPT_ATTACK, attemptAttack)
        }

    private var variantData: NbtCompound
        get() = dataTracker.get(VARIANT_DATA)
        set(value) {
            dataTracker.set(VARIANT_DATA, value)
        }

    private var variantKey: String
        get() = if (dataTracker.get(VARIANT).isBlank() && assumeDefault) {
            variants.keys.first()
        } else dataTracker.get(VARIANT)
        private set(value) {
            dataTracker.set(VARIANT, value)
        }

    @Suppress("UNUSED_PARAMETER")
    var variant: FishVariant?
        get() = variants[variantKey]
        private set(value) {}

    // endregion

    public override fun getNextAirOnLand(air: Int): Int {
        return this.maxAir
    }

    protected open fun hasSelfControl(): Boolean {
        return true
    }

    protected open fun getMinSize(): Int {
        return 0
    }

    protected open fun getMaxSize(): Int {
        return 0
    }

    override fun registerControllers(controllerRegistrar: AnimatableManager.ControllerRegistrar) {
        controllerRegistrar.add(
            AnimationController(
                this,
                "controller",
                0,
                ::predicate
            )
        )
    }

    override fun getAnimatableInstanceCache(): AnimatableInstanceCache {
        return factory
    }

    init {
        moveControl = FishMoveControl(this)
    }

    override fun travel(movementInput: Vec3d?) {
        if (this.canMoveVoluntarily() && this.isTouchingWater) {
            this.updateVelocity(0.01f, movementInput)
            this.move(MovementType.SELF, this.velocity)
            this.velocity = velocity.multiply(0.9)
            if (this.target == null) {
                this.velocity = velocity.add(0.0, -0.005, 0.0)
            }
        } else {
            super.travel(movementInput)
        }
    }

    override fun tickMovement() {
        if (!this.isTouchingWater && this.isOnGround && this.verticalCollision) {
            this.velocity = velocity.add(
                ((random.nextFloat() * 2.0f - 1.0f) * 0.05f).toDouble(), 0.4000000059604645,
                ((random.nextFloat() * 2.0f - 1.0f) * 0.05f).toDouble()
            )
            this.isOnGround = false
            this.velocityDirty = true
            this.playSound(this.flopSound, this.soundVolume, this.soundPitch)
        }

        super.tickMovement()
    }

    open fun speedModifier(): Double {
        return 0.0
    }

    internal class FishMoveControl(private val fish: HybridAquaticFishEntity) : MoveControl(fish) {
        override fun tick() {
            if (fish.isSubmergedIn(FluidTags.WATER)) {
                fish.velocity = fish.velocity.add(0.0, 0.005, 0.0)
            }

            if (this.state == State.MOVE_TO && !fish.navigation.isIdle) {
                val f = (this.speed * fish.getAttributeValue(EntityAttributes.GENERIC_MOVEMENT_SPEED)).toFloat()
                fish.movementSpeed =
                    MathHelper.lerp(0.125f, fish.movementSpeed, f)
                val d = this.targetX - fish.x
                val e = this.targetY - fish.y
                val g = this.targetZ - fish.z
                if (e != 0.0) {
                    val h = sqrt(d * d + e * e + g * g)
                    fish.velocity = fish.velocity.add(
                        0.0,
                        fish.movementSpeed.toDouble() * (e / h) * 0.1, 0.0
                    )
                }

                if (d != 0.0 || g != 0.0) {
                    val i = (MathHelper.atan2(g, d) * 57.2957763671875).toFloat() - 90.0f
                    fish.yaw = this.wrapDegrees(fish.yaw, i, 90.0f)
                    fish.bodyYaw = fish.yaw
                }
            } else {
                fish.movementSpeed = 0.0f
            }
        }
    }

    internal class SwimToRandomPlaceGoal(private val fish: HybridAquaticFishEntity) : SwimAroundGoal(fish, 1.0, 40) {
        override fun canStart(): Boolean {
            return fish.hasSelfControl() && super.canStart()
        }
    }

    internal class AttackGoal(private val fish: HybridAquaticFishEntity) : MeleeAttackGoal(fish, 1.0,true) {
        override fun canStart(): Boolean {
            return !fish.fromFishingNet && super.canStart()
        }

        override fun attack(target: LivingEntity, squaredDistance: Double) {
            val d = getSquaredMaxAttackDistance(target)
            if (squaredDistance <= d && this.isCooledDown) {
                resetCooldown()
                mob.tryAttack(target)
                fish.isSprinting = true
                fish.attemptAttack = true

                if (target.health <= 0)
                    fish.eatFish(target.type)
            }
        }

        override fun getSquaredMaxAttackDistance(entity: LivingEntity): Double {
            return (1.25f + entity.width).toDouble()
        }

        override fun start() {
            super.start()
            fish.attemptAttack = false
        }

        override fun stop() {
            super.stop()
            fish.attemptAttack = false
        }
    }

    override fun tryAttack(target: Entity?): Boolean {
        if (super.tryAttack(target)) {

            playSound(SoundEvents.ENTITY_FOX_EAT,1.0F,0.0F)

            return true
        } else {
            return false
        }
    }

    companion object {
        val MOISTNESS: TrackedData<Int> = DataTracker.registerData(HybridAquaticFishEntity::class.java, TrackedDataHandlerRegistry.INTEGER)
        val FISH_SIZE: TrackedData<Int> = DataTracker.registerData(HybridAquaticFishEntity::class.java, TrackedDataHandlerRegistry.INTEGER)
        val HUNGER: TrackedData<Int> = DataTracker.registerData(HybridAquaticFishEntity::class.java, TrackedDataHandlerRegistry.INTEGER)
        val ATTEMPT_ATTACK: TrackedData<Boolean> = DataTracker.registerData(HybridAquaticFishEntity::class.java, TrackedDataHandlerRegistry.BOOLEAN)
        val VARIANT: TrackedData<String> = DataTracker.registerData(HybridAquaticFishEntity::class.java, TrackedDataHandlerRegistry.STRING)
        var VARIANT_DATA: TrackedData<NbtCompound> = DataTracker.registerData(HybridAquaticFishEntity::class.java, TrackedDataHandlerRegistry.NBT_COMPOUND)

        const val MAX_HUNGER = 1200
        const val HUNGER_KEY = "Hunger"
        const val MOISTNESS_KEY = "Moistness"
        const val VARIANT_KEY = "Variant"
        const val VARIANT_DATA_KEY = "VariantData"
        const val FISH_SIZE_KEY = "FishSize"

        val ATTACK_ANIMATION: RawAnimation  = RawAnimation.begin().then("attack", Animation.LoopType.LOOP)
        val SWIM_ANIMATION: RawAnimation  = RawAnimation.begin().then("swim", Animation.LoopType.LOOP)
        val FLOP_ANIMATION: RawAnimation  = RawAnimation.begin().then("flop", Animation.LoopType.LOOP)

        @Suppress("UNUSED_PARAMETER", "DEPRECATION")
        fun canSpawn(
            type: EntityType<out WaterCreatureEntity>,
            world: WorldAccess,
            reason: SpawnReason?,
            pos: BlockPos,
            random: Random?
        ): Boolean {
            val topY = world.seaLevel
            val bottomY = world.seaLevel - 24

            return pos.y in bottomY..topY &&
                    world.getFluidState(pos.down()).isIn(FluidTags.WATER) &&
                    world.getBlockState(pos.up()).isOf(Blocks.WATER)
        }

        @Suppress("UNUSED_PARAMETER", "DEPRECATION")
        fun canUndergroundSpawn(
            type: EntityType<out WaterCreatureEntity?>?,
            world: WorldAccess,
            reason: SpawnReason?,
            pos: BlockPos,
            random: Random?
        ): Boolean {
            return pos.y <= world.seaLevel - 32 &&
                    world.getBaseLightLevel(pos, 0) == 0 &&
                    world.getBlockState(pos).isOf(Blocks.WATER)
        }

        fun getScaleAdjustment(fish: HybridAquaticFishEntity, adjustment: Float): Float {
            return 1.0f + (fish.size * adjustment)
        }

    }

    @Suppress("UNUSED")
    data class FishVariant(
        val variantName : String,
        val spawnCondition: (WorldAccess, SpawnReason, BlockPos, Random ) -> Boolean,
        val ignore: List<Ignore> = emptyList(),
        val priority: Int = 0,
        var providedVariant: (WorldAccess, SpawnReason, BlockPos, Random, HybridAquaticFishEntity) -> String = {_,_,_,_,_ ->
            variantName
        }
    ) {
        companion object {
            /**
             * Creates a biome variant of a fish
             */
            fun biomeVariant(variantName: String, biomes : TagKey<Biome>, ignore : List<Ignore> = emptyList()): FishVariant {
                return FishVariant(variantName, { world, _, pos, _ ->
                    world.getBiome(pos).isIn(biomes)
                }, ignore)
            }
        }

        enum class Ignore {
            TEXTURE,
            MODEL,
            ANIMATION
        }
    }

    @Suppress("UNUSED")
    data class VariantCollisionRules(val variants : Set<String>, val collisionHandler: (Set<String>, Random, ServerWorldAccess) -> String, val exclusionStatus: ExclusionStatus = INCLUSIVE) {

        /**
         * INCLUSIVE - all other variants can exist within this selection swath
         * <pre> </pre>
         * EXCLUSIVE - all other variants are excluded from this selection swath
         */
        enum class ExclusionStatus {
            INCLUSIVE,
            EXCLUSIVE
        }

        /**
         * <pre></pre>
         * Example:
         * ```kotlin
         * // returns a bluefin or a yellowfin tuna variant
         * equalDistribution(setOf("bluefin", "yellowfin"))
         * ```
         * @return a random variant within the set
         */
        fun equalDistribution(variants: Set<String>, status : ExclusionStatus = INCLUSIVE) : VariantCollisionRules {
            return VariantCollisionRules(variants, { possibleVariants, _, _ ->
                possibleVariants.random()
            }, status)
        }

        /**
         * Example
         * ```
         * weightedDistribution(setOf(
         *  Pair("bluefin", 0.80),
         *  Pair("yellowfin", 0.20)
         * ))
         * ```
         * @return a premade variant collision rule which allows weighted distribution of variants.
         */
        fun weightedDistribution(weights: Set<Pair<String, Double>>, status: ExclusionStatus = EXCLUSIVE) : VariantCollisionRules {
            return VariantCollisionRules(weights.map { pair -> pair.first }.toSet(), { _, random, _ ->
                // sum up weights
                val weightTotal = weights.sumOf { pair -> pair.second }
                val randomVal = random.nextFloat() * weightTotal
                var accumulatedWeight = 0.0
                var result = ""

                for (pair in weights) {
                    accumulatedWeight += pair.second
                    if (randomVal < accumulatedWeight) {
                        result = pair.first
                        break
                    }
                }

                result
            }, status)
        }
    }



}