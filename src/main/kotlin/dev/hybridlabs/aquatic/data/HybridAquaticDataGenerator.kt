package dev.hybridlabs.aquatic.data

import dev.hybridlabs.aquatic.HybridAquatic
import dev.hybridlabs.aquatic.data.client.LanguageProvider
import dev.hybridlabs.aquatic.data.client.ModelProvider
import dev.hybridlabs.aquatic.data.server.ConfiguredFeatureProvider
import dev.hybridlabs.aquatic.data.server.PlacedFeatureProvider
import dev.hybridlabs.aquatic.data.server.RecipeProvider
import dev.hybridlabs.aquatic.data.server.SeaMessageProvider
import dev.hybridlabs.aquatic.data.server.loot.BlockLootTableProvider
import dev.hybridlabs.aquatic.data.server.loot.EntityTypeLootTableProvider
import dev.hybridlabs.aquatic.data.server.loot.FishingLootTableProvider
import dev.hybridlabs.aquatic.data.server.loot.GenericLootTableProvider
import dev.hybridlabs.aquatic.data.server.tag.BiomeTagProvider
import dev.hybridlabs.aquatic.data.server.tag.BlockTagProvider
import dev.hybridlabs.aquatic.data.server.tag.EntityTypeTagProvider
import dev.hybridlabs.aquatic.data.server.tag.ItemTagProvider
import dev.hybridlabs.aquatic.registry.HybridAquaticRegistryKeys
import dev.hybridlabs.aquatic.world.biome.HybridAquaticBiomes
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator
import net.minecraft.registry.Registry
import net.minecraft.registry.RegistryBuilder
import net.minecraft.registry.RegistryKeys

object HybridAquaticDataGenerator : DataGeneratorEntrypoint {
    override fun onInitializeDataGenerator(generator: FabricDataGenerator) {
        val pack = generator.createPack()
        pack.addProvider(::LanguageProvider)
        pack.addProvider(::ModelProvider)
        pack.addProvider(::BlockLootTableProvider)
        pack.addProvider(::EntityTypeLootTableProvider)
        pack.addProvider(::FishingLootTableProvider)
        pack.addProvider(::GenericLootTableProvider)
        pack.addProvider(::BiomeTagProvider)
        pack.addProvider(::BlockTagProvider)
        pack.addProvider(::ItemTagProvider)
        pack.addProvider(::EntityTypeTagProvider)
        pack.addProvider(::ConfiguredFeatureProvider)
        pack.addProvider(::PlacedFeatureProvider)
        pack.addProvider(::RecipeProvider)
        pack.addProvider(::SeaMessageProvider)
    }

    override fun buildRegistry(registryBuilder: RegistryBuilder) {
        registryBuilder.addRegistry(HybridAquaticRegistryKeys.SEA_MESSAGE) {}
        registryBuilder.addRegistry(RegistryKeys.BIOME, HybridAquaticBiomes::bootstrap)
    }

    fun <T> filterHybridAquatic(registry: Registry<T>): (T) -> Boolean {
        return { o ->
            val id = registry.getId(o)
            id?.namespace == HybridAquatic.MOD_ID
        }
    }
}
