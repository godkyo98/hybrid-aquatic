package dev.hybridlabs.aquatic.client.model.entity

import dev.hybridlabs.aquatic.entity.fish.HybridAquaticFishEntity
import net.minecraft.util.Identifier

class UmbrellaOctopusEntityModel : HybridAquaticFishEntityModel<HybridAquaticFishEntity>("umbrella_octopus") {
    override fun getTextureResource(animatable: HybridAquaticFishEntity?): Identifier {
        if (animatable != null) return getVariantTexture(allVariants[animatable.variant])
        return super.getTextureResource(animatable)
    }

    companion object {
        val allVariants: Array<String> = arrayOf("white", "brown", "orange", "yellow", "pink", "purple")
    }
}

