package dev.hybridlabs.aquatic.entity.fish

import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.entity.EntityType
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.mob.WaterCreatureEntity
import net.minecraft.sound.SoundEvent
import net.minecraft.sound.SoundEvents
import net.minecraft.world.World

class UmbrellaOctopusEntity(entityType: EntityType<out UmbrellaOctopusEntity>, world: World) :
    HybridAquaticFishEntity(entityType, world, 6, HybridAquaticEntityTags.NONE, HybridAquaticEntityTags.OCTOPUS_PREDATOR) {
    companion object {
        fun createMobAttributes(): DefaultAttributeContainer.Builder {
            return WaterCreatureEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 4.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.5)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 1.0)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 12.0)
        }
    }

    override fun shouldFlopOnLand(): Boolean {
        return false
    }

    override fun getHurtSound(source: DamageSource): SoundEvent {
        return SoundEvents.ENTITY_SQUID_HURT
    }

    override fun getDeathSound(): SoundEvent {
        return SoundEvents.ENTITY_SQUID_DEATH
    }

    override fun getAmbientSound(): SoundEvent {
        return SoundEvents.ENTITY_SQUID_AMBIENT
    }

    override fun getSplashSound(): SoundEvent {
        return SoundEvents.ENTITY_DOLPHIN_SPLASH
    }

    override fun getSwimSound(): SoundEvent {
        return SoundEvents.ENTITY_SQUID_AMBIENT
    }

    override fun getMaxSize(): Int {
        return 5
    }

    override fun getMinSize(): Int {
        return -5
    }
}