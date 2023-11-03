package dev.hybridlabs.aquatic.client.render.block.entity

import dev.hybridlabs.aquatic.block.entity.BuoyBlockEntity
import dev.hybridlabs.aquatic.client.model.block.entity.BuoyBlockEntityModel
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory.Context
import software.bernie.geckolib.renderer.GeoBlockRenderer

class BuoyBlockEntityRenderer(context: Context) : GeoBlockRenderer<BuoyBlockEntity>(BuoyBlockEntityModel()) {
}