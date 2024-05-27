@file:Suppress("UnstableApiUsage")

package dev.hybridlabs.aquatic.data.server

import dev.hybridlabs.aquatic.block.HybridAquaticBlocks
import dev.hybridlabs.aquatic.tag.HybridAquaticBlockTags
import dev.hybridlabs.aquatic.world.gen.feature.HybridAquaticConfiguredFeatures
import dev.hybridlabs.aquatic.world.gen.feature.HybridAquaticFeatures
import dev.hybridlabs.aquatic.world.gen.feature.MessageInABottleFeatureConfig
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider
import net.minecraft.block.Blocks
import net.minecraft.registry.RegistryWrapper
import net.minecraft.state.property.Properties
import net.minecraft.util.math.intprovider.ConstantIntProvider
import net.minecraft.world.gen.CountConfig
import net.minecraft.world.gen.blockpredicate.BlockPredicate
import net.minecraft.world.gen.feature.*
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize
import net.minecraft.world.gen.foliage.JungleFoliagePlacer
import net.minecraft.world.gen.stateprovider.BlockStateProvider
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider
import net.minecraft.world.gen.trunk.StraightTrunkPlacer
import java.util.concurrent.CompletableFuture

class ConfiguredFeatureProvider(output: FabricDataOutput, registriesFuture: CompletableFuture<RegistryWrapper.WrapperLookup>) : FabricDynamicRegistryProvider(output, registriesFuture) {
    override fun configure(registries: RegistryWrapper.WrapperLookup, entries: Entries) {
        // anemone patch
        entries.add(
            HybridAquaticConfiguredFeatures.ANEMONE_PATCH,
            ConfiguredFeature(
                Feature.NO_BONEMEAL_FLOWER, RandomPatchFeatureConfig(
                    2, 2, 2,
                    PlacedFeatures.createEntry(
                        Feature.SIMPLE_BLOCK,
                        SimpleBlockFeatureConfig(
                            BlockStateProvider.of(HybridAquaticBlocks.ANEMONE.defaultState.with(Properties.WATERLOGGED, true))
                        ),
                        BlockPredicate.matchingBlockTag(HybridAquaticBlockTags.ANEMONES_GENERATE_IN)
                    )
                )
            )
        )

        entries.add(
            HybridAquaticConfiguredFeatures.COCONUT_PALM,
            ConfiguredFeature(
                Feature.TREE,
                TreeFeatureConfig.Builder(
                    BlockStateProvider.of(HybridAquaticBlocks.COCONUT_PALM_LOG),
                    StraightTrunkPlacer(5, 4, 3),
                    BlockStateProvider.of(HybridAquaticBlocks.COCONUT_PALM_LEAVES),
                    JungleFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(1), 2),
                    TwoLayersFeatureSize(1,0,2)
                )
                    .dirtProvider(BlockStateProvider.of(Blocks.SAND))
                    .build()
            )
        )


        // tube sponge patch
        entries.add(
            HybridAquaticConfiguredFeatures.TUBE_SPONGE_PATCH,
            ConfiguredFeature(
                Feature.FLOWER,
                RandomPatchFeatureConfig(
                    4, 2, 2,
                    PlacedFeatures.createEntry(
                        Feature.SIMPLE_BLOCK,
                        SimpleBlockFeatureConfig(
                            BlockStateProvider.of(HybridAquaticBlocks.TUBE_SPONGE.defaultState.with(Properties.WATERLOGGED, true))
                        ),
                        BlockPredicate.matchingBlockTag(HybridAquaticBlockTags.TUBE_SPONGE_GENERATE_IN)
                    )
                )
            )
        )

        // giant clam patch
        entries.add(
            HybridAquaticConfiguredFeatures.GIANT_CLAM_PATCH,
            ConfiguredFeature(
                Feature.NO_BONEMEAL_FLOWER, RandomPatchFeatureConfig(
                    2, 2, 2,
                    PlacedFeatures.createEntry(
                        Feature.SIMPLE_BLOCK,
                        SimpleBlockFeatureConfig(
                            BlockStateProvider.of(HybridAquaticBlocks.GIANT_CLAM.defaultState.with(Properties.WATERLOGGED, true))
                        ),
                        BlockPredicate.matchingBlockTag(HybridAquaticBlockTags.GIANT_CLAM_GENERATE_IN)
                    )
                )
            )
        )

        // hydrothermal vents
        entries.add(
            HybridAquaticConfiguredFeatures.HYDROTHERMAL_VENTS,
            ConfiguredFeature(
                Feature.SEA_PICKLE, CountConfig(9
                )
            )
        )

        // message in a bottle
        entries.add(
            HybridAquaticConfiguredFeatures.MESSAGE_IN_A_BOTTLE,
            ConfiguredFeature(
                HybridAquaticFeatures.MESSAGE_IN_A_BOTTLE, MessageInABottleFeatureConfig(
                    SimpleBlockStateProvider.of(HybridAquaticBlocks.MESSAGE_IN_A_BOTTLE)
                )
            )
        )
    }

    override fun getName(): String {
        return "Configured Features"
    }
}
