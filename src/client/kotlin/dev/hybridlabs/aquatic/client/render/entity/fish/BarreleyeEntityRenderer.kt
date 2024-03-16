package dev.hybridlabs.aquatic.client.render.entity.fish

import dev.hybridlabs.aquatic.client.model.entity.BarreleyeEntityModel
import dev.hybridlabs.aquatic.entity.fish.HybridAquaticFishEntity
import net.minecraft.client.render.entity.EntityRendererFactory.Context

class BarreleyeEntityRenderer(context: Context) : HybridAquaticFishEntityRenderer<HybridAquaticFishEntity>(context, BarreleyeEntityModel(), true, true)