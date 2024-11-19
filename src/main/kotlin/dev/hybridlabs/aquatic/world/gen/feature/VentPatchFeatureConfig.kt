package dev.hybridlabs.aquatic.world.gen.feature

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import dev.hybridlabs.aquatic.block.TubeWormBlock
import net.minecraft.util.math.intprovider.IntProvider
import net.minecraft.world.gen.feature.FeatureConfig
import net.minecraft.world.gen.stateprovider.BlockStateProvider

data class VentPatchFeatureConfig(
    val baseProvider: BlockStateProvider,
    val ventProvider: BlockStateProvider,
    val wormProvider: BlockStateProvider,
    val count: IntProvider,
    val spreadRadius: IntProvider,
    val wormCount: IntProvider,
    val wormSpreadRadius: IntProvider,
    val minWorms: Int,
    val maxWorms: Int,
) : FeatureConfig {
    companion object {
        val CODEC: Codec<VentPatchFeatureConfig> = RecordCodecBuilder.create { instance ->
            instance.group(
                BlockStateProvider.TYPE_CODEC.fieldOf("base_block").forGetter(VentPatchFeatureConfig::baseProvider),
                BlockStateProvider.TYPE_CODEC.fieldOf("vent_block").forGetter(VentPatchFeatureConfig::ventProvider),
                BlockStateProvider.TYPE_CODEC.fieldOf("worm_block").forGetter(VentPatchFeatureConfig::wormProvider),
                IntProvider.POSITIVE_CODEC.fieldOf("count").forGetter(VentPatchFeatureConfig::count),
                IntProvider.POSITIVE_CODEC.fieldOf("spread_radius").forGetter(VentPatchFeatureConfig::spreadRadius),
                IntProvider.POSITIVE_CODEC.fieldOf("worm_count").forGetter(VentPatchFeatureConfig::wormCount),
                IntProvider.POSITIVE_CODEC.fieldOf("worm_spread_radius").forGetter(VentPatchFeatureConfig::wormSpreadRadius),
                Codec.intRange(1, TubeWormBlock.MAX_WORMS).fieldOf("min_worms").forGetter(VentPatchFeatureConfig::minWorms),
                Codec.intRange(1, TubeWormBlock.MAX_WORMS).fieldOf("max_worms").forGetter(VentPatchFeatureConfig::maxWorms),
            ).apply(instance, ::VentPatchFeatureConfig)
        }
    }
}
