package dev.hybridlabs.aquatic.entity.fish

import dev.hybridlabs.aquatic.tag.HybridAquaticBiomeTags
import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.entity.EntityType
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.mob.WaterCreatureEntity
import net.minecraft.util.Identifier
import net.minecraft.world.World

class SeahorseEntity(entityType: EntityType<out SeahorseEntity>, world: World) :
    HybridAquaticFishEntity(entityType, world, variants = hashMapOf(
        "common" to FishVariant.biomeVariant("common", HybridAquaticBiomeTags.REEF,
            ignore = listOf(FishVariant.Ignore.ANIMATION)),
        "big_belly" to FishVariant.biomeVariant("big_belly", HybridAquaticBiomeTags.REEF,
            ignore = listOf(FishVariant.Ignore.ANIMATION)),
        "thorny" to FishVariant.biomeVariant("thorny", HybridAquaticBiomeTags.REEF,
            ignore = listOf(FishVariant.Ignore.ANIMATION)),
        "pygmy" to FishVariant.biomeVariant("pygmy", HybridAquaticBiomeTags.REEF,
            ignore = listOf(FishVariant.Ignore.ANIMATION)),
        ),
        HybridAquaticEntityTags.NONE, HybridAquaticEntityTags.NONE) {

    public override fun getLootTableId(): Identifier {
        return when (this.variant?.variantName) {
            "common" -> Identifier("hybrid-aquatic", "gameplay/seahorse")
            "big_belly" -> Identifier("hybrid-aquatic", "gameplay/seahorse")
            "thorny" -> Identifier("hybrid-aquatic", "gameplay/seahorse")
            "pygmy" -> Identifier("hybrid-aquatic", "gameplay/seahorse")
            else -> super.getLootTableId()
        }
    }

    override fun getLimitPerChunk(): Int {
        return 2
    }

    companion object {
        fun createMobAttributes(): DefaultAttributeContainer.Builder {
            return WaterCreatureEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 2.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.5)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 1.0)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 12.0)
        }
    }
}