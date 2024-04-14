package dev.hybridlabs.aquatic.entity.cephalopod

import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.entity.EntityType
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.mob.WaterCreatureEntity
import net.minecraft.world.World

class FireflySquidEntity(entityType: EntityType<out FireflySquidEntity>, world: World) :
    HybridAquaticCephalopodEntity(entityType, world, 1, HybridAquaticEntityTags.FIREFLY_SQUID_PREY, HybridAquaticEntityTags.FIREFLY_SQUID_PREDATOR, true) {

    companion object {
        fun createMobAttributes(): DefaultAttributeContainer.Builder {
            return WaterCreatureEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 6.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.8)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 1.0)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 12.0)
        }
    }

    override fun initGoals() {
        super.initGoals()
        goalSelector.add(1, AttackGoal(this))
    }

    override fun getMaxSize(): Int {
        return 5
    }

    override fun getMinSize(): Int {
        return -5
    }
}