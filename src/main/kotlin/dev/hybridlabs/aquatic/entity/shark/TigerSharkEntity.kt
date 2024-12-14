package dev.hybridlabs.aquatic.entity.shark

import dev.hybridlabs.aquatic.item.HybridAquaticItems
import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.ai.goal.RevengeGoal
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.mob.WaterCreatureEntity
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.server.world.ServerWorld
import net.minecraft.sound.SoundEvents
import net.minecraft.world.World
import kotlin.random.Random

class TigerSharkEntity(entityType: EntityType<out TigerSharkEntity>, world: World) :
    HybridAquaticSharkEntity(
        entityType,
        world,
        listOf(
            HybridAquaticEntityTags.CEPHALOPOD,
            HybridAquaticEntityTags.CRUSTACEAN,
            HybridAquaticEntityTags.MEDIUM_PREY
        ),
        false,
        false
    ) {

    private var burpTimer = 0
    private var burpPending = false

    override fun tick() {
        super.tick()

        if (burpPending && burpTimer > 0) {
            burpTimer--
            if (burpTimer == 0) {
                dropBurpItem()
                burpPending = false
            }
        }
    }

    private fun dropBurpItem() {
        if (!world.isClient) {
            val itemsToDrop = listOf(
                ItemStack(Items.ACACIA_BOAT),
                ItemStack(Items.BIRCH_BOAT),
                ItemStack(Items.CHERRY_BOAT),
                ItemStack(Items.DARK_OAK_BOAT),
                ItemStack(Items.JUNGLE_BOAT),
                ItemStack(Items.MANGROVE_BOAT),
                ItemStack(Items.OAK_BOAT),
                ItemStack(Items.SPRUCE_BOAT),
                ItemStack(Items.ROTTEN_FLESH),
                ItemStack(Items.LEATHER),
                ItemStack(Items.GLASS_BOTTLE),
                ItemStack(Items.SCUTE),
                ItemStack(Items.NAUTILUS_SHELL),
                ItemStack(Items.SKELETON_SKULL),
                ItemStack(Items.BONE),
                ItemStack(Items.PRISMARINE_CRYSTALS),
                ItemStack(Items.PRISMARINE_SHARD),
                ItemStack(Items.BRUSH),
                ItemStack(Items.NAME_TAG),
                ItemStack(Items.COMPASS),
                ItemStack(Items.CLOCK),
                ItemStack(Items.SPYGLASS),
                ItemStack(Items.SADDLE),
                ItemStack(HybridAquaticItems.SHARK_TOOTH),
                ItemStack(HybridAquaticItems.GLOWING_HOOK),
                ItemStack(HybridAquaticItems.BARBED_HOOK),
            )

            val randomItem = itemsToDrop.random(Random)

            this.dropStack(randomItem)

            this.playSound(SoundEvents.ENTITY_PLAYER_BURP, 1.0f, 1.0f)
        }
    }

    override fun onKilledOther(world: ServerWorld, killedEntity: LivingEntity): Boolean {
        val result = super.onKilledOther(world, killedEntity)

        burpTimer = 60
        burpPending = true

        return result
    }

    override fun initGoals() {
        super.initGoals()
        goalSelector.add(1, RevengeGoal(this))
    }

    companion object {
        fun createMobAttributes(): DefaultAttributeContainer.Builder {
            return WaterCreatureEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 54.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.8)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 8.0)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 16.0)
        }
    }

    override fun getMaxSize(): Int {
        return 2
    }

    override fun getMinSize(): Int {
        return -2
    }
}
