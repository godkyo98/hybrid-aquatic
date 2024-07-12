package dev.hybridlabs.aquatic.data.server.tag

import dev.hybridlabs.aquatic.block.HybridAquaticBlocks
import dev.hybridlabs.aquatic.data.HybridAquaticDataGenerator.filterHybridAquatic
import dev.hybridlabs.aquatic.item.HybridAquaticItems
import dev.hybridlabs.aquatic.tag.HybridAquaticItemTags
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.minecraft.item.Items
import net.minecraft.registry.Registries
import net.minecraft.registry.RegistryWrapper
import net.minecraft.registry.tag.ItemTags
import java.util.concurrent.CompletableFuture

class ItemTagProvider(output: FabricDataOutput, registriesFuture: CompletableFuture<RegistryWrapper.WrapperLookup>) : FabricTagProvider.ItemTagProvider(output, registriesFuture) {
    override fun configure(arg: RegistryWrapper.WrapperLookup) {
        // Items that will be consumed by a fishing rod
        setOf(
            HybridAquaticItems.BARBED_HOOK,
            HybridAquaticItems.GLOWING_HOOK,
            HybridAquaticItems.MAGNETIC_HOOK,
            HybridAquaticItems.CREEPERMAGNET_HOOK,
            HybridAquaticItems.OMINOUS_HOOK
        ).forEach { item ->
            getOrCreateTagBuilder(HybridAquaticItemTags.LURE_ITEMS).add(item)
        }

        //region wood

        getOrCreateTagBuilder(ItemTags.PLANKS)
            .add(HybridAquaticBlocks.DRIFTWOOD_PLANKS.asItem())

        getOrCreateTagBuilder(ItemTags.LOGS_THAT_BURN)
            .add(HybridAquaticBlocks.DRIFTWOOD_LOG.asItem())
            .add(HybridAquaticBlocks.DRIFTWOOD_WOOD.asItem())
            .add(HybridAquaticBlocks.STRIPPED_DRIFTWOOD_LOG.asItem())
            .add(HybridAquaticBlocks.STRIPPED_DRIFTWOOD_WOOD.asItem())

        getOrCreateTagBuilder(HybridAquaticItemTags.DRIFTWOOD_LOG_WOOD)
            .add(HybridAquaticItems.DRIFTWOOD_LOG)
            .add(HybridAquaticItems.STRIPPED_DRIFTWOOD_LOG)
            .add(HybridAquaticItems.DRIFTWOOD_WOOD)
            .add(HybridAquaticItems.STRIPPED_DRIFTWOOD_WOOD)

        //endregion

        listOf(
            HybridAquaticItems.LIONFISH,
            HybridAquaticItems.ROCKFISH,
            HybridAquaticItems.BLUE_SPOTTED_STINGRAY,
            HybridAquaticItems.DRAGONFISH,
            HybridAquaticItems.NEEDLEFISH,
            HybridAquaticItems.PIRANHA,
            HybridAquaticItems.ANGLERFISH,
            HybridAquaticItems.BARRELEYE,
            HybridAquaticItems.BLUE_TANG,
            HybridAquaticItems.CLOWNFISH,
            HybridAquaticItems.UNICORNFISH,
            HybridAquaticItems.COWFISH,
            HybridAquaticItems.TIGER_BARB,
            HybridAquaticItems.OSCAR,
            HybridAquaticItems.FLASHLIGHT_FISH,
            HybridAquaticItems.RATFISH,
            HybridAquaticItems.SURGEONFISH_ORANGESHOULDER,
            HybridAquaticItems.SURGEONFISH_SOHAL,
            HybridAquaticItems.DISCUS,
            HybridAquaticItems.GOURAMI,
            HybridAquaticItems.BETTA,
            HybridAquaticItems.DANIO,
            HybridAquaticItems.NEON_TETRA,
            HybridAquaticItems.DRAGONFISH,
        ).forEach { item ->
            getOrCreateTagBuilder(HybridAquaticItemTags.SMALL_FISH).add(item)
        }

        listOf(
            HybridAquaticItems.TUNA,
            HybridAquaticItems.MAHI_MAHI,
            HybridAquaticItems.OPAH,
            HybridAquaticItems.MORAY_EEL,
            HybridAquaticItems.TRIGGERFISH,
        ).forEach { item ->
            getOrCreateTagBuilder(HybridAquaticItemTags.MEDIUM_FISH).add(item)
        }

        listOf(
            HybridAquaticItems.COCONUT_CRAB_CLAW,
            HybridAquaticItems.DUNGENESS_CRAB_CLAW,
            HybridAquaticItems.FIDDLER_CRAB_CLAW,
            HybridAquaticItems.FLOWER_CRAB_CLAW,
            HybridAquaticItems.GHOST_CRAB_CLAW,
            HybridAquaticItems.LIGHTFOOT_CRAB_CLAW,
            HybridAquaticItems.SPIDER_CRAB_CLAW,
            HybridAquaticItems.VAMPIRE_CRAB_CLAW,
            HybridAquaticItems.YETI_CRAB_CLAW,
        ).forEach { item ->
            getOrCreateTagBuilder(HybridAquaticItemTags.CRAB_CLAW).add(item)
        }

        listOf(
            HybridAquaticItems.ANGLERFISH,
            HybridAquaticItems.BARRELEYE,
            HybridAquaticItems.BETTA,
            HybridAquaticItems.BLUE_SPOTTED_STINGRAY,
            HybridAquaticItems.BLUE_TANG,
            HybridAquaticItems.CLOWNFISH,
            HybridAquaticItems.DANIO,
            HybridAquaticItems.DISCUS,
            HybridAquaticItems.DRAGONFISH,
            HybridAquaticItems.FLASHLIGHT_FISH,
            HybridAquaticItems.GOURAMI,
            HybridAquaticItems.LIONFISH,
            HybridAquaticItems.MAHI_MAHI,
            HybridAquaticItems.MORAY_EEL,
            HybridAquaticItems.NEEDLEFISH,
            HybridAquaticItems.OPAH,
            HybridAquaticItems.OSCAR,
            HybridAquaticItems.PIRANHA,
            HybridAquaticItems.RATFISH,
            HybridAquaticItems.ROCKFISH,
            HybridAquaticItems.NEON_TETRA,
            HybridAquaticItems.TIGER_BARB,
            HybridAquaticItems.TRIGGERFISH,
            HybridAquaticItems.TUNA,
            HybridAquaticItems.UNICORNFISH,
            HybridAquaticItems.RAW_FISH_MEAT,
            HybridAquaticItems.RAW_FISH_STEAK,
            HybridAquaticItems.RAW_TENTACLE,
            HybridAquaticItems.RAW_CRAB,
            HybridAquaticItems.RAW_SHRIMP,
            HybridAquaticItems.RAW_LOBSTER,
            HybridAquaticItems.RAW_CRAYFISH,
        ).forEach { item ->
            getOrCreateTagBuilder(HybridAquaticItemTags.RAW_FISHES).add(item)
        }

        listOf(
            HybridAquaticItems.COOKED_FISH_MEAT,
            HybridAquaticItems.COOKED_FISH_STEAK,
            HybridAquaticItems.COOKED_TENTACLE,
            HybridAquaticItems.COOKED_CRAB,
            HybridAquaticItems.COOKED_SHRIMP,
            HybridAquaticItems.COOKED_LOBSTER,
            HybridAquaticItems.COOKED_CRAYFISH,
        ).forEach { item ->
            getOrCreateTagBuilder(HybridAquaticItemTags.COOKED_FISHES).add(item)
        }

        listOf(
            HybridAquaticItems.RAW_CRAB,
            HybridAquaticItems.RAW_SHRIMP,
            HybridAquaticItems.RAW_LOBSTER,
            HybridAquaticItems.RAW_CRAYFISH,
            HybridAquaticItems.RAW_TENTACLE,
            HybridAquaticItems.RAW_FISH_MEAT,
            HybridAquaticItems.RAW_FISH_STEAK,
            HybridAquaticItems.ANGLERFISH,
            HybridAquaticItems.BARRELEYE,
            HybridAquaticItems.BETTA,
            HybridAquaticItems.BLUE_SPOTTED_STINGRAY,
            HybridAquaticItems.BLUE_TANG,
            HybridAquaticItems.CLOWNFISH,
            HybridAquaticItems.DANIO,
            HybridAquaticItems.DISCUS,
            HybridAquaticItems.DRAGONFISH,
            HybridAquaticItems.FLASHLIGHT_FISH,
            HybridAquaticItems.GOURAMI,
            HybridAquaticItems.LIONFISH,
            HybridAquaticItems.MAHI_MAHI,
            HybridAquaticItems.MORAY_EEL,
            HybridAquaticItems.NEEDLEFISH,
            HybridAquaticItems.OPAH,
            HybridAquaticItems.OSCAR,
            HybridAquaticItems.PIRANHA,
            HybridAquaticItems.RATFISH,
            HybridAquaticItems.ROCKFISH,
            HybridAquaticItems.NEON_TETRA,
            HybridAquaticItems.TIGER_BARB,
            HybridAquaticItems.TRIGGERFISH,
            HybridAquaticItems.TUNA,
            HybridAquaticItems.UNICORNFISH,
            Items.PORKCHOP,
            Items.BEEF,
            Items.MUTTON,
            Items.CHICKEN,
            Items.RABBIT,
            Items.COD,
            Items.SALMON,
            Items.TROPICAL_FISH,
            Items.ROTTEN_FLESH,
        ).forEach { item ->
            getOrCreateTagBuilder(HybridAquaticItemTags.CRUSTACEAN_TEMPT_ITEMS).add(item)
        }

        listOf(
            HybridAquaticItems.ANGLERFISH,
            HybridAquaticItems.BARRELEYE,
            HybridAquaticItems.CLOWNFISH,
            HybridAquaticItems.ROCKFISH,
            HybridAquaticItems.TUNA,
            HybridAquaticItems.BLUE_TANG,
            HybridAquaticItems.UNICORNFISH,
            HybridAquaticItems.TRIGGERFISH,
            HybridAquaticItems.OSCAR,
            HybridAquaticItems.OPAH,
            HybridAquaticItems.MAHI_MAHI,
            HybridAquaticItems.NEEDLEFISH,
            HybridAquaticItems.BLUE_SPOTTED_STINGRAY,
            HybridAquaticItems.LIONFISH,
            HybridAquaticItems.MORAY_EEL,
            HybridAquaticItems.PIRANHA,
            HybridAquaticItems.NEON_TETRA,
            HybridAquaticItems.DANIO,
            HybridAquaticItems.GOURAMI,
            HybridAquaticItems.BETTA,
            HybridAquaticItems.DISCUS,
            HybridAquaticItems.RATFISH,
            HybridAquaticItems.TIGER_BARB,
        ).forEach { item ->
            getOrCreateTagBuilder(ItemTags.FISHES).add(item)
        }

        // plushies
        Registries.ITEM
            .filter(filterHybridAquatic(Registries.ITEM))
            .filter { item ->
                val id = Registries.ITEM.getId(item)
                id.path.endsWith("plushie")
            }
            .forEach { item ->
                getOrCreateTagBuilder(HybridAquaticItemTags.PLUSHIES).add(item)
            }

        setOf(
            Items.IRON_AXE,
            Items.IRON_PICKAXE,
            Items.IRON_SHOVEL,
            Items.IRON_HOE,
            Items.IRON_SWORD,
        ).forEach { item ->
            getOrCreateTagBuilder(HybridAquaticItemTags.IRON_TOOLS).add(item)
        }
    }
}
