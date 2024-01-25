package dev.hybridlabs.aquatic.data.server.tag

import dev.hybridlabs.aquatic.data.HybridAquaticDataGenerator.filterHybridAquatic
import dev.hybridlabs.aquatic.item.HybridAquaticItems
import dev.hybridlabs.aquatic.tag.HybridAquaticItemTags
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.minecraft.item.Items
import net.minecraft.registry.Registries
import net.minecraft.registry.RegistryWrapper
import java.util.concurrent.CompletableFuture

class ItemTagProvider(output: FabricDataOutput, registriesFuture: CompletableFuture<RegistryWrapper.WrapperLookup>) : FabricTagProvider.ItemTagProvider(output, registriesFuture) {
    override fun configure(arg: RegistryWrapper.WrapperLookup) {
        // Items that will be consumed by a fishing rod
        setOf(
            HybridAquaticItems.BARBED_HOOK,
            HybridAquaticItems.GLOWING_HOOK,
            HybridAquaticItems.MAGNETIC_HOOK
        ).forEach { item ->
            getOrCreateTagBuilder(HybridAquaticItemTags.LURE_ITEMS).add(item)
        }

        listOf(
            HybridAquaticItems.LIONFISH,
            HybridAquaticItems.ROCKFISH,
            HybridAquaticItems.BLUE_SPOTTED_STINGRAY,
            HybridAquaticItems.NEEDLEFISH,
            HybridAquaticItems.PIRANHA,
            HybridAquaticItems.ANGLERFISH,
            HybridAquaticItems.BARRELEYE,
            HybridAquaticItems.BLUE_TANG,
            HybridAquaticItems.CLOWNFISH,
            HybridAquaticItems.UNICORN_FISH,
            HybridAquaticItems.COWFISH,
            HybridAquaticItems.TIGER_BARB,
            HybridAquaticItems.OSCAR,
        ).forEach { item ->
            getOrCreateTagBuilder(HybridAquaticItemTags.SMALL_FISH).add(item)
        }

        listOf(
            HybridAquaticItems.YELLOWFIN_TUNA,
            HybridAquaticItems.MAHI_MAHI,
            HybridAquaticItems.OPAH,
            HybridAquaticItems.MORAY_EEL,
            HybridAquaticItems.TRIGGERFISH,
        ).forEach { item ->
            getOrCreateTagBuilder(HybridAquaticItemTags.MEDIUM_FISH).add(item)
        }

        listOf(
            HybridAquaticItems.DUNGENESS_CRAB_CLAW,
            HybridAquaticItems.GHOST_CRAB_CLAW,
            HybridAquaticItems.FLOWER_CRAB_CLAW,
            HybridAquaticItems.SPIDER_CRAB_CLAW,
            HybridAquaticItems.VAMPIRE_CRAB_CLAW,
            HybridAquaticItems.FIDDLER_CRAB_CLAW,
            HybridAquaticItems.COCONUT_CRAB_CLAW,
        ).forEach { item ->
            getOrCreateTagBuilder(HybridAquaticItemTags.CRAB_CLAW).add(item)
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
