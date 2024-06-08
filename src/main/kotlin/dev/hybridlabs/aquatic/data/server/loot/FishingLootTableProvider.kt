package dev.hybridlabs.aquatic.data.server.loot

import dev.hybridlabs.aquatic.item.HybridAquaticItems
import dev.hybridlabs.aquatic.loot.HybridAquaticLootTables
import dev.hybridlabs.aquatic.loot.entry.MessageInABottleItemEntry
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider
import net.minecraft.loot.LootPool
import net.minecraft.loot.LootTable
import net.minecraft.loot.context.LootContextTypes
import net.minecraft.loot.entry.ItemEntry
import net.minecraft.util.Identifier
import java.util.function.BiConsumer

class FishingLootTableProvider(output: FabricDataOutput) : SimpleFabricLootTableProvider(output, LootContextTypes.FISHING) {
    override fun accept(exporter: BiConsumer<Identifier, LootTable.Builder>) {
        // fishing fish loot table extension
        exporter.accept(
            HybridAquaticLootTables.FISHING_FISH_ID,
            LootTable.builder()
                .randomSequenceId(HybridAquaticLootTables.FISHING_FISH_ID)
                .pool(
                    LootPool.builder()
                        .with(
                            ItemEntry.builder(HybridAquaticItems.LIONFISH)
                                .weight(5)
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.GOURAMI)
                                .weight(5)
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.FLASHLIGHT_FISH)
                                .weight(5)
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.DISCUS)
                                .weight(5)
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.BETTA)
                                .weight(5)
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.DANIO)
                                .weight(5)
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.TETRA)
                                .weight(5)
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.MAHI_MAHI)
                                .weight(3)
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.TUNA)
                                .weight(3)
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.OPAH)
                                .weight(3)
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.ROCKFISH)
                                .weight(5)
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.BLUE_SPOTTED_STINGRAY)
                                .weight(2)
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.MORAY_EEL)
                                .weight(2)
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.NEEDLEFISH)
                                .weight(4)
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.PIRANHA)
                                .weight(2)
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.ANGLERFISH)
                                .weight(2)
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.BARRELEYE)
                                .weight(1)
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.BLUE_TANG)
                                .weight(4)
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.CLOWNFISH)
                                .weight(4)
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.UNICORN_FISH)
                                .weight(4)
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.COWFISH)
                                .weight(2)
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.TRIGGERFISH)
                                .weight(3)
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.TIGER_BARB)
                                .weight(2)
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.OSCAR)
                                .weight(2)
                        )
                )
        )

        // fishing treasure loot table extension
        exporter.accept(
            HybridAquaticLootTables.FISHING_TREASURE_ID,
            LootTable.builder()
                .randomSequenceId(HybridAquaticLootTables.FISHING_TREASURE_ID)
                .pool(
                    LootPool.builder()
                        .with(ItemEntry.builder(HybridAquaticItems.HYBRID_CRATE))
                        .with(ItemEntry.builder(HybridAquaticItems.OAK_CRATE))
                        .with(ItemEntry.builder(HybridAquaticItems.SPRUCE_CRATE))
                        .with(ItemEntry.builder(HybridAquaticItems.BIRCH_CRATE))
                        .with(ItemEntry.builder(HybridAquaticItems.DARK_OAK_CRATE))
                        .with(ItemEntry.builder(HybridAquaticItems.ACACIA_CRATE))
                        .with(ItemEntry.builder(HybridAquaticItems.JUNGLE_CRATE))
                        .with(ItemEntry.builder(HybridAquaticItems.MANGROVE_CRATE))
                        .with(ItemEntry.builder(HybridAquaticItems.CHERRY_CRATE))
                        .with(MessageInABottleItemEntry.builder())
                )
        )
    }
}
