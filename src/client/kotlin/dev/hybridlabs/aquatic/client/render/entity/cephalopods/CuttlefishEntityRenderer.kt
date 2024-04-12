package dev.hybridlabs.aquatic.client.render.entity.cephalopods

import dev.hybridlabs.aquatic.client.model.entity.CuttlefishEntityModel
import dev.hybridlabs.aquatic.client.render.entity.fish.HybridAquaticFishEntityRenderer
import dev.hybridlabs.aquatic.entity.cephalopod.HybridAquaticCephalopodEntity
import dev.hybridlabs.aquatic.entity.fish.HybridAquaticFishEntity
import net.minecraft.client.render.entity.EntityRendererFactory.Context

class CuttlefishEntityRenderer(context: Context) : HybridAquaticCephalopodEntityRenderer<HybridAquaticCephalopodEntity>(context, CuttlefishEntityModel(), true, false)

