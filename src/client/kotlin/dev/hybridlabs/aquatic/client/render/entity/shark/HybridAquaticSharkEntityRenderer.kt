package dev.hybridlabs.aquatic.client.render.entity.shark

import dev.hybridlabs.aquatic.entity.shark.HybridAquaticSharkEntity
import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.render.entity.EntityRendererFactory
import net.minecraft.client.util.math.MatrixStack
import software.bernie.geckolib.model.GeoModel
import software.bernie.geckolib.renderer.GeoEntityRenderer
import software.bernie.geckolib.renderer.layer.AutoGlowingGeoLayer

@Suppress("LeakingThis")
open class HybridAquaticSharkEntityRenderer<T: HybridAquaticSharkEntity>(context: EntityRendererFactory.Context, model: GeoModel<T>, private var variableSize: Boolean = false, canGlow: Boolean = false): GeoEntityRenderer<T>(context, model) {

    init {
        if(canGlow) addRenderLayer(AutoGlowingGeoLayer(this))
    }

    override fun render(
        entity: T,
        entityYaw: Float,
        partialTick: Float,
        poseStack: MatrixStack,
        bufferSource: VertexConsumerProvider,
        packedLight: Int
    ) {
        if(variableSize) {
            val size = HybridAquaticSharkEntity.getScaleAdjustment(entity, 0.05f)
            poseStack.scale(size, size, size)
        }
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight)
    }
}