package dev.hybridlabs.aquatic.client.model.entity.jellyfish

import dev.hybridlabs.aquatic.entity.jellyfish.HybridAquaticJellyfishEntity
import net.minecraft.client.render.RenderLayer
import net.minecraft.util.Identifier

class BoxJellyfishEntityModel : HybridAquaticJellyfishEntityModel<HybridAquaticJellyfishEntity>("box_jellyfish") {
    override fun getRenderType(animatable: HybridAquaticJellyfishEntity?, texture: Identifier?): RenderLayer {
        return RenderLayer.getEntityTranslucent(texture)
    }
}
