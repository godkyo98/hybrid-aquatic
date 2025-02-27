package dev.hybridlabs.aquatic.client.model.entity.miniboss

import dev.hybridlabs.aquatic.HybridAquatic
import dev.hybridlabs.aquatic.entity.miniboss.HybridAquaticMinibossEntity
import net.minecraft.util.Identifier
import software.bernie.geckolib.model.GeoModel

abstract class HybridAquaticMinibossEntityModel<T: HybridAquaticMinibossEntity> (private val id: String) : GeoModel<T>() {
    override fun getModelResource(animatable: T?): Identifier {
        return Identifier(HybridAquatic.MOD_ID, "geo/miniboss/${id}/$id.geo.json")
    }

    override fun getTextureResource(animatable: T?): Identifier {
        return Identifier(HybridAquatic.MOD_ID, "textures/entity/miniboss/${id}/$id.png")
    }

    fun getVariantTexture(variant: String): Identifier {
        return Identifier(HybridAquatic.MOD_ID, "textures/entity/miniboss/${id}/${id}_$variant.png")
    }

    override fun getAnimationResource(animatable: T?): Identifier {
        return Identifier(HybridAquatic.MOD_ID, "animations/$id.animation.json")
    }
}