package dev.hybridlabs.aquatic.entity.miniboss

import net.minecraft.enchantment.EnchantmentHelper
import net.minecraft.enchantment.Enchantments
import net.minecraft.entity.EntityGroup
import net.minecraft.entity.EntityType
import net.minecraft.entity.ai.control.MoveControl
import net.minecraft.entity.ai.goal.LookAroundGoal
import net.minecraft.entity.ai.goal.LookAtEntityGoal
import net.minecraft.entity.ai.goal.WanderAroundGoal
import net.minecraft.entity.ai.pathing.EntityNavigation
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.boss.BossBar
import net.minecraft.entity.boss.ServerBossBar
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.data.DataTracker
import net.minecraft.entity.data.TrackedData
import net.minecraft.entity.data.TrackedDataHandlerRegistry
import net.minecraft.entity.mob.WaterCreatureEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.nbt.NbtCompound
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.text.Text
import net.minecraft.world.Difficulty
import net.minecraft.world.World
import software.bernie.geckolib.core.animatable.GeoAnimatable
import software.bernie.geckolib.core.animation.AnimationState
import software.bernie.geckolib.core.`object`.PlayState

class KarkinosEntity(entityType: EntityType<out HybridAquaticMinibossEntity>, world: World) :
    HybridAquaticMinibossEntity(entityType, world) {

    private var landNavigation: EntityNavigation = createNavigation(world)

    init {
        stepHeight = 2.0F
        moveControl = MoveControl(this)
        navigation = this.landNavigation
    }

    private var flipTimer: Int = 0
    private val flipDuration: Int = 40
    private var bossBar: ServerBossBar = ServerBossBar(displayName, BossBar.Color.RED, BossBar.Style.NOTCHED_10)
    private var isFlipped: Boolean
        get() = dataTracker.get(FLIPPED)
        set(bool) = dataTracker.set(FLIPPED, bool)

    override fun initDataTracker() {
        super.initDataTracker()
        dataTracker.startTracking(FLIPPED, false)
    }

    override fun initGoals() {
        goalSelector.add(1, KarkinosAttackGoal(this))
        goalSelector.add(2, WanderAroundGoal(this, 0.5))
        goalSelector.add(2, LookAroundGoal(this))
        goalSelector.add(2, LookAtEntityGoal(this, PlayerEntity::class.java, 16.0f))
    }

    override fun getGroup(): EntityGroup? {
        return EntityGroup.ARTHROPOD
    }

    private fun beFlipped() {
        isFlipped = true
        flipTimer = flipDuration
    }

    override fun hasNoDrag(): Boolean {
        return false
    }

    override fun shouldSwimInFluids(): Boolean {
        return false
    }

    override fun mobTick() {
        if (!world.isClient && isFlipped) {
            target = null

            flipTimer--
            attributes.getCustomInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED)?.baseValue = 0.0
            attributes.getCustomInstance(EntityAttributes.GENERIC_ARMOR_TOUGHNESS)?.baseValue = 0.0
            attributes.getCustomInstance(EntityAttributes.GENERIC_ARMOR)?.baseValue = 0.0

            if (flipTimer <= 0) {
                isFlipped = false
            }
        }

        bossBar.percent = health / maxHealth

        super.mobTick()
    }

    override fun onStartedTrackingBy(player: ServerPlayerEntity) {
        super.onStartedTrackingBy(player)
        bossBar.addPlayer(player)
    }

    override fun onStoppedTrackingBy(player: ServerPlayerEntity) {
        super.onStoppedTrackingBy(player)
        bossBar.removePlayer(player)
    }

    override fun checkDespawn() {
        if (world.difficulty == Difficulty.PEACEFUL && this.isDisallowedInPeaceful) {
            discard()
        } else {
            despawnCounter = 0
        }
    }

    override fun isPushable(): Boolean =
        this.isFlipped

    override fun <E : GeoAnimatable> predicate(event: AnimationState<E>): PlayState {
        if (isFlipped) {
            event.controller.setAnimation(FLIPPED_ANIMATION)
            return PlayState.CONTINUE
        }

        return super.predicate(event)
    }

    override fun getMovementSpeed(): Float {
        return if (isFlipped) 0.0f else super.getMovementSpeed()
    }

    override fun damage(source: DamageSource, amount: Float): Boolean {val damaged = super.damage(source, amount)

        if (damaged && source.source is PlayerEntity && !isFlipped) {
            val player = source.source as PlayerEntity
            val heldItem = player.mainHandStack

            if (EnchantmentHelper.getLevel(Enchantments.BANE_OF_ARTHROPODS, heldItem) > 2 ||
                (EnchantmentHelper.getLevel(Enchantments.RIPTIDE, heldItem) > 0)
            ) {
                beFlipped()
            }
        }

        return damaged
    }

    override fun readCustomDataFromNbt(nbt: NbtCompound) {
        if (hasCustomName()) {
            bossBar.name = this.displayName
        }
        super.readCustomDataFromNbt(nbt)
    }

    override fun setCustomName(name: Text?) {
        super.setCustomName(name)
        bossBar.name = this.displayName
    }

    companion object {
        fun createMobAttributes(): DefaultAttributeContainer.Builder {
            return WaterCreatureEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 100.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.5)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 5.0)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 24.0)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 10.0)
                .add(EntityAttributes.GENERIC_ARMOR_TOUGHNESS, 5.0)
                .add(EntityAttributes.GENERIC_ARMOR, 8.0)
        }

        val FLIPPED: TrackedData<Boolean> = DataTracker.registerData(KarkinosEntity::class.java, TrackedDataHandlerRegistry.BOOLEAN)
    }

    private class KarkinosAttackGoal(private val karkinos: KarkinosEntity) : AttackGoal(karkinos) {
        override fun shouldContinue(): Boolean {
            return !karkinos.isFlipped && super.shouldContinue()
        }
    }
}