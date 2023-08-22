package dev.hybridlabs.aquatic.entity.fish

import net.minecraft.entity.EntityType
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.mob.WaterCreatureEntity
import net.minecraft.sound.SoundEvent
import net.minecraft.sound.SoundEvents
import net.minecraft.world.World

class VampireSquidEntity(entityType: EntityType<out VampireSquidEntity>, world: World) : HybridAquaticFishEntity(entityType, world) {
    companion object {
        fun createMobAttributes(): DefaultAttributeContainer.Builder {
            return WaterCreatureEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 12.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 1.1)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2.0)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 20.0)

        }
    }

    override fun getHurtSound(source: DamageSource): SoundEvent {
        return SoundEvents.ENTITY_GLOW_SQUID_SQUIRT
    }
    override fun getDeathSound(): SoundEvent {
        return SoundEvents.ENTITY_GLOW_SQUID_DEATH
    }
    override fun getAmbientSound(): SoundEvent {
        return SoundEvents.ENTITY_GLOW_SQUID_AMBIENT
    }
    override fun getSplashSound(): SoundEvent {
        return SoundEvents.ENTITY_DOLPHIN_SPLASH
    }
    override fun getSwimSound(): SoundEvent {
        return SoundEvents.ENTITY_SQUID_AMBIENT
    }
}