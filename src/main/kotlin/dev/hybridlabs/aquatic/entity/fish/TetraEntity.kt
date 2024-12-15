package dev.hybridlabs.aquatic.entity.fish

import dev.hybridlabs.aquatic.tag.HybridAquaticBiomeTags
import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.entity.EntityType
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.world.World

class TetraEntity(entityType: EntityType<out TetraEntity>, world: World) :
    HybridAquaticSchoolingFishEntity(
        entityType, world,
        listOf(
            HybridAquaticEntityTags.NONE
        ),
        listOf(
            HybridAquaticEntityTags.MEDIUM_PREY,
            HybridAquaticEntityTags.LARGE_PREY,
            HybridAquaticEntityTags.SHARK
        ),
        variants = hashMapOf(
            "neon" to FishVariant.biomeVariant(
                "neon",
                listOf(
                    HybridAquaticBiomeTags.JUNGLE,
                    HybridAquaticBiomeTags.MARSHES,
                    HybridAquaticBiomeTags.TROPICAL_RIVERS
                ),
                ignore = listOf(FishVariant.Ignore.ANIMATION, FishVariant.Ignore.MODEL)
            ),
            "cave" to FishVariant.biomeVariant(
                "cave", listOf(HybridAquaticBiomeTags.CAVES),
                ignore = listOf(FishVariant.Ignore.ANIMATION, FishVariant.Ignore.MODEL)
            )
        )
    ) {

    override fun getLimitPerChunk(): Int {
        return 3
    }

    companion object {
        fun createMobAttributes(): DefaultAttributeContainer.Builder {
            return createLivingAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 3.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.6)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 1.0)
                .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 0.0)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 4.0)
        }
    }
}