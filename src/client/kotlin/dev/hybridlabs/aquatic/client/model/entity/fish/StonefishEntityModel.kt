package dev.hybridlabs.aquatic.client.model.entity.fish

import dev.hybridlabs.aquatic.entity.fish.HybridAquaticFishEntity
import net.minecraft.util.Identifier

class StonefishEntityModel : dev.hybridlabs.aquatic.client.model.entity.fish.HybridAquaticFishEntityModel<HybridAquaticFishEntity>("stonefish") {
    override fun getTextureResource(animatable: HybridAquaticFishEntity?): Identifier {
        if (animatable != null) return getVariantTexture(dev.hybridlabs.aquatic.client.model.entity.fish.StonefishEntityModel.Companion.allVariants[animatable.variant])
        return super.getTextureResource(animatable)
    }

    companion object {
        val allVariants: Array<String> = arrayOf("brown", "gray", "green")
    }
}

