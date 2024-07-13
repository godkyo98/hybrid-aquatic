package dev.hybridlabs.aquatic.mixin.client;

import dev.hybridlabs.aquatic.HybridAquatic;
import dev.hybridlabs.aquatic.access.CustomFishingBobberEntityData;
import dev.hybridlabs.aquatic.item.HybridAquaticItems;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.FishingBobberEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FishingBobberEntityRenderer.class)
public abstract class FishingBobberEntityRendererMixin {
  @Unique
  private static final RenderLayer BARBED_HOOK_LAYER = getRenderLayer(new Identifier(HybridAquatic.MOD_ID, "textures/entity/bobber/barbed_bobber.png"));
  @Unique
  private static final RenderLayer GLOWING_HOOK_LAYER = getRenderLayer(new Identifier(HybridAquatic.MOD_ID, "textures/entity/bobber/glowing_bobber.png"));
  @Unique
  private static final RenderLayer MAGNETIC_HOOK_LAYER = getRenderLayer(new Identifier(HybridAquatic.MOD_ID, "textures/entity/bobber/magnetic_bobber.png"));
  @Unique
  private static final RenderLayer CREEPERMAGNET_HOOK_LAYER = getRenderLayer(new Identifier(HybridAquatic.MOD_ID, "textures/entity/bobber/creepermagnet_bobber.png"));
  @Unique
  private static final RenderLayer OMINOUS_HOOK_LAYER = getRenderLayer(new Identifier(HybridAquatic.MOD_ID, "textures/entity/bobber/ominous_bobber.png"));
  
  @Unique
  FishingBobberEntity entity;
  
  @Inject(method = "render(Lnet/minecraft/entity/projectile/FishingBobberEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V", at = @At(
    value = "HEAD"
  ))
  private void objectGetter(FishingBobberEntity fishingBobberEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, CallbackInfo ci) {
    entity = fishingBobberEntity;
  }
  
  @Redirect(method = "render(Lnet/minecraft/entity/projectile/FishingBobberEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V", at = @At(
    value = "INVOKE", target = "Lnet/minecraft/client/render/VertexConsumerProvider;getBuffer(Lnet/minecraft/client/render/RenderLayer;)Lnet/minecraft/client/render/VertexConsumer;", ordinal = 0
  ))
  private VertexConsumer changeRenderLayer(VertexConsumerProvider instance, RenderLayer renderLayer) {
    RenderLayer currentRenderLayer = renderLayer;

    ItemStack currentStack = ((CustomFishingBobberEntityData) entity).hybrid_aquatic$getLureItem();
    if (currentStack.getItem().equals(HybridAquaticItems.INSTANCE.getBARBED_HOOK())) currentRenderLayer = BARBED_HOOK_LAYER;
    else if (currentStack.getItem().equals(HybridAquaticItems.INSTANCE.getGLOWING_HOOK())) currentRenderLayer = GLOWING_HOOK_LAYER;
    else if (currentStack.getItem().equals(HybridAquaticItems.INSTANCE.getMAGNETIC_HOOK())) currentRenderLayer = MAGNETIC_HOOK_LAYER;
    else if (currentStack.getItem().equals(HybridAquaticItems.INSTANCE.getCREEPERMAGNET_HOOK())) currentRenderLayer = CREEPERMAGNET_HOOK_LAYER;
    else if (currentStack.getItem().equals(HybridAquaticItems.INSTANCE.getOMINOUS_HOOK())) currentRenderLayer = OMINOUS_HOOK_LAYER;
    
    return instance.getBuffer(currentRenderLayer);
  }
  
  @Unique
  private static RenderLayer getRenderLayer(Identifier textureLocation) {
    return RenderLayer.getEntityCutout(textureLocation);
  }
}
