package dev.hybridlabs.aquatic.data.server.tag

import dev.hybridlabs.aquatic.entity.HybridAquaticEntityTypes
import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.minecraft.entity.EntityType
import net.minecraft.registry.RegistryWrapper
import java.util.concurrent.CompletableFuture

class EntityTypeTagProvider(output: FabricDataOutput, registriesFuture: CompletableFuture<RegistryWrapper.WrapperLookup>) : FabricTagProvider.EntityTypeTagProvider(output, registriesFuture) {
    override fun configure(arg: RegistryWrapper.WrapperLookup) {
        // prey source
        getOrCreateTagBuilder(HybridAquaticEntityTags.SMALL_PREY)
            .add(
                HybridAquaticEntityTypes.CLOWNFISH,
                HybridAquaticEntityTypes.COWFISH,
                HybridAquaticEntityTypes.FLASHLIGHT_FISH,
                HybridAquaticEntityTypes.MACKEREL,
                HybridAquaticEntityTypes.BARRELEYE,
                HybridAquaticEntityTypes.BETTA,
                HybridAquaticEntityTypes.TETRA,
                HybridAquaticEntityTypes.PIRANHA,
                HybridAquaticEntityTypes.DANIO,
                HybridAquaticEntityTypes.TIGER_BARB,
                HybridAquaticEntityTypes.SURGEONFISH,
                HybridAquaticEntityTypes.DISCUS,
                HybridAquaticEntityTypes.GOURAMI,
                HybridAquaticEntityTypes.CUTTLEFISH,
                HybridAquaticEntityTypes.DRAGONFISH,
                HybridAquaticEntityTypes.ANGLERFISH,
                EntityType.SALMON,
                EntityType.COD,
                EntityType.TROPICAL_FISH
            )

        getOrCreateTagBuilder(HybridAquaticEntityTags.MEDIUM_PREY)
            .add(
                HybridAquaticEntityTypes.RATFISH,
                HybridAquaticEntityTypes.STINGRAY,
                HybridAquaticEntityTypes.TRIGGERFISH,
                HybridAquaticEntityTypes.NEEDLEFISH,
                HybridAquaticEntityTypes.ROCKFISH,
                HybridAquaticEntityTypes.SEA_BASS,
                HybridAquaticEntityTypes.LIONFISH,
                HybridAquaticEntityTypes.PARROTFISH,
                HybridAquaticEntityTypes.MORAY_EEL,
                EntityType.SQUID,
                EntityType.GLOW_SQUID
            )

        getOrCreateTagBuilder(HybridAquaticEntityTags.LARGE_PREY)
            .add(
                HybridAquaticEntityTypes.SUNFISH,
                HybridAquaticEntityTypes.OARFISH,
                HybridAquaticEntityTypes.OPAH,
                HybridAquaticEntityTypes.TUNA,
                HybridAquaticEntityTypes.MAHI,
                EntityType.TURTLE,
            )

        getOrCreateTagBuilder(HybridAquaticEntityTags.CRUSTACEAN)
            .add(
                HybridAquaticEntityTypes.COCONUT_CRAB,
                HybridAquaticEntityTypes.DUNGENESS_CRAB,
                HybridAquaticEntityTypes.FIDDLER_CRAB,
                HybridAquaticEntityTypes.FLOWER_CRAB,
                HybridAquaticEntityTypes.GHOST_CRAB,
                HybridAquaticEntityTypes.HERMIT_CRAB,
                HybridAquaticEntityTypes.HORSESHOE_CRAB,
                HybridAquaticEntityTypes.LIGHTFOOT_CRAB,
                HybridAquaticEntityTypes.SPIDER_CRAB,
                HybridAquaticEntityTypes.VAMPIRE_CRAB,
                HybridAquaticEntityTypes.GIANT_ISOPOD,
                HybridAquaticEntityTypes.YETI_CRAB,
                HybridAquaticEntityTypes.DECORATOR_CRAB,
            )

        getOrCreateTagBuilder(HybridAquaticEntityTags.CEPHALOPOD)
            .add(
                HybridAquaticEntityTypes.VAMPIRE_SQUID,
                HybridAquaticEntityTypes.FIREFLY_SQUID,
                HybridAquaticEntityTypes.CUTTLEFISH,
                HybridAquaticEntityTypes.GLOWING_SUCKER_OCTOPUS,
                HybridAquaticEntityTypes.UMBRELLA_OCTOPUS,
                HybridAquaticEntityTypes.NAUTILUS,
            )

        getOrCreateTagBuilder(HybridAquaticEntityTags.NONE)

        // sharks
        getOrCreateTagBuilder(HybridAquaticEntityTags.SHARK)
            .add(
                HybridAquaticEntityTypes.BASKING_SHARK,
                HybridAquaticEntityTypes.BULL_SHARK,
                HybridAquaticEntityTypes.FRILLED_SHARK,
                HybridAquaticEntityTypes.GREAT_WHITE_SHARK,
                HybridAquaticEntityTypes.HAMMERHEAD_SHARK,
                HybridAquaticEntityTypes.THRESHER_SHARK,
                HybridAquaticEntityTypes.TIGER_SHARK,
                HybridAquaticEntityTypes.WHALE_SHARK,
            )

        // critters

        getOrCreateTagBuilder(HybridAquaticEntityTags.CRITTER)
            .add(
                HybridAquaticEntityTypes.NUDIBRANCH,
                HybridAquaticEntityTypes.SEA_CUCUMBER,
                HybridAquaticEntityTypes.SEA_URCHIN,
                HybridAquaticEntityTypes.STARFISH,
            )

        // jellyfish
        getOrCreateTagBuilder(HybridAquaticEntityTags.JELLYFISH)
            .add(
                HybridAquaticEntityTypes.ATOLLA_JELLYFISH,
                HybridAquaticEntityTypes.BARREL_JELLYFISH,
                HybridAquaticEntityTypes.BLUE_JELLYFISH,
                HybridAquaticEntityTypes.CAULIFLOWER_JELLYFISH,
                HybridAquaticEntityTypes.COMPASS_JELLYFISH,
                HybridAquaticEntityTypes.FRIED_EGG_JELLYFISH,
                HybridAquaticEntityTypes.LIONS_MANE_JELLYFISH,
                HybridAquaticEntityTypes.MAUVE_STINGER,
                HybridAquaticEntityTypes.MOON_JELLYFISH,
                HybridAquaticEntityTypes.NOMURA_JELLYFISH,
                HybridAquaticEntityTypes.SEA_NETTLE,
            )
    }
}
