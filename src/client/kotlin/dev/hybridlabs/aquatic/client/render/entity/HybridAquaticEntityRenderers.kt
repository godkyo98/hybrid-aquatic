package dev.hybridlabs.aquatic.client.render.entity

import dev.hybridlabs.aquatic.client.render.entity.cephalopods.*
import dev.hybridlabs.aquatic.client.render.entity.critter.NudibranchEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.critter.SeaCucumberEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.critter.SeaUrchinEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.critter.StarfishEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.crustacean.*
import dev.hybridlabs.aquatic.client.render.entity.fish.*
import dev.hybridlabs.aquatic.client.render.entity.jellyfish.*
import dev.hybridlabs.aquatic.client.render.entity.miniboss.KarkinosEntityRenderer
import dev.hybridlabs.aquatic.client.render.entity.shark.*
import dev.hybridlabs.aquatic.entity.HybridAquaticEntityTypes
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry

object HybridAquaticEntityRenderers {
    //region fish
    val ANGLERFISH = EntityRendererRegistry.register(HybridAquaticEntityTypes.ANGLERFISH, ::AnglerfishEntityRenderer)
    val DRAGONFISH = EntityRendererRegistry.register(HybridAquaticEntityTypes.DRAGONFISH, ::DragonfishEntityRenderer)
    val PIRANHA = EntityRendererRegistry.register(HybridAquaticEntityTypes.PIRANHA, ::PiranhaEntityRenderer)
    val BARRELEYE = EntityRendererRegistry.register(HybridAquaticEntityTypes.BARRELEYE, ::BarreleyeEntityRenderer)
    val CLOWNFISH = EntityRendererRegistry.register(HybridAquaticEntityTypes.CLOWNFISH, ::ClownfishEntityRenderer)
    val TUNA = EntityRendererRegistry.register(HybridAquaticEntityTypes.TUNA, ::TunaEntityRenderer)
    val FLASHLIGHT_FISH = EntityRendererRegistry.register(HybridAquaticEntityTypes.FLASHLIGHT_FISH, ::FlashlightfishEntityRenderer)
    val LIONFISH = EntityRendererRegistry.register(HybridAquaticEntityTypes.LIONFISH, ::LionfishEntityRenderer)
    val OARFISH = EntityRendererRegistry.register(HybridAquaticEntityTypes.OARFISH, ::OarfishEntityRenderer)
    val SEA_ANGEL = EntityRendererRegistry.register(HybridAquaticEntityTypes.SEA_ANGEL, ::SeaAngelEntityRenderer)
    val OPAH = EntityRendererRegistry.register(HybridAquaticEntityTypes.OPAH, ::OpahEntityRenderer)
    val SUNFISH = EntityRendererRegistry.register(HybridAquaticEntityTypes.SUNFISH, ::SunfishEntityRenderer)
    val MAHIMAHI = EntityRendererRegistry.register(HybridAquaticEntityTypes.MAHIMAHI, ::MahiMahiEntityRenderer)
    val MORAY_EEL = EntityRendererRegistry.register(HybridAquaticEntityTypes.MORAY_EEL, ::MorayEelEntityRenderer)
    val ROCKFISH = EntityRendererRegistry.register(HybridAquaticEntityTypes.ROCKFISH, ::RockfishEntityRenderer)
    val TIGER_BARB = EntityRendererRegistry.register(HybridAquaticEntityTypes.TIGER_BARB, ::TigerBarbEntityRenderer)
    val NEEDLEFISH = EntityRendererRegistry.register(HybridAquaticEntityTypes.NEEDLEFISH, ::NeedlefishEntityRenderer)
    val RATFISH = EntityRendererRegistry.register(HybridAquaticEntityTypes.RATFISH, ::RatfishEntityRenderer)
    val TRIGGERFISH = EntityRendererRegistry.register(HybridAquaticEntityTypes.TRIGGERFISH, ::TriggerfishEntityRenderer)
    val OSCAR = EntityRendererRegistry.register(HybridAquaticEntityTypes.OSCAR, ::OscarEntityRenderer)
    val DANIO = EntityRendererRegistry.register(HybridAquaticEntityTypes.DANIO, ::DanioEntityRenderer)
    val TOADFISH = EntityRendererRegistry.register(HybridAquaticEntityTypes.TOADFISH, ::ToadfishEntityRenderer)
    val TETRA = EntityRendererRegistry.register(HybridAquaticEntityTypes.TETRA, ::TetraEntityRenderer)
    val STONEFISH = EntityRendererRegistry.register(HybridAquaticEntityTypes.STONEFISH, ::StonefishEntityRenderer)
    val BETTA = EntityRendererRegistry.register(HybridAquaticEntityTypes.BETTA, ::BettaEntityRenderer)
    val SEAHORSE = EntityRendererRegistry.register(HybridAquaticEntityTypes.SEAHORSE, ::SeahorseEntityRenderer)
    val MOON_JELLYFISH = EntityRendererRegistry.register(HybridAquaticEntityTypes.MOON_JELLYFISH, ::MoonJellyfishEntityRenderer)
    val GOURAMI = EntityRendererRegistry.register(HybridAquaticEntityTypes.GOURAMI, ::GouramiEntityRenderer)
    val COWFISH = EntityRendererRegistry.register(HybridAquaticEntityTypes.COWFISH, ::CowfishEntityRenderer)
    val DISCUS = EntityRendererRegistry.register(HybridAquaticEntityTypes.DISCUS, ::DiscusEntityRenderer)
    val SURGEONFISH = EntityRendererRegistry.register(HybridAquaticEntityTypes.SURGEONFISH, ::SurgeonfishEntityRenderer)
    val BLUE_SPOTTED_STINGRAY = EntityRendererRegistry.register(HybridAquaticEntityTypes.BLUE_SPOTTED_STINGRAY, ::BlueSpottedStingrayEntityRenderer)

    //endregion

    //region cephalopods
    val CUTTLEFISH = EntityRendererRegistry.register(HybridAquaticEntityTypes.CUTTLEFISH, ::CuttlefishEntityRenderer)
    val UMBRELLA_OCTOPUS = EntityRendererRegistry.register(HybridAquaticEntityTypes.UMBRELLA_OCTOPUS, ::UmbrellaOctopusEntityRenderer)
    val VAMPIRE_SQUID = EntityRendererRegistry.register(HybridAquaticEntityTypes.VAMPIRE_SQUID, ::VampireSquidEntityRenderer)
    val NAUTILUS = EntityRendererRegistry.register(HybridAquaticEntityTypes.NAUTILUS, ::NautilusEntityRenderer)
    val FIREFLY_SQUID = EntityRendererRegistry.register(HybridAquaticEntityTypes.FIREFLY_SQUID, ::FireflySquidEntityRenderer)
    val GLOWING_SUCKER_OCTOPUS = EntityRendererRegistry.register(HybridAquaticEntityTypes.GLOWING_SUCKER_OCTOPUS, ::GlowingSuckerOctopusEntityRenderer)

    //endregion

    //region jellyfish
    val SEA_NETTLE = EntityRendererRegistry.register(HybridAquaticEntityTypes.SEA_NETTLE, ::SeaNettleEntityRenderer)
    val FRIED_EGG_JELLYFISH = EntityRendererRegistry.register(HybridAquaticEntityTypes.FRIED_EGG_JELLYFISH, ::FriedEggJellyfishEntityRenderer)
    val CAULIFLOWER_JELLYFISH = EntityRendererRegistry.register(HybridAquaticEntityTypes.CAULIFLOWER_JELLYFISH, ::CauliflowerJellyfishEntityRenderer)
    val NOMURA_JELLYFISH = EntityRendererRegistry.register(HybridAquaticEntityTypes.NOMURA_JELLYFISH, ::NomuraJellyfishEntityRenderer)
    val BARREL_JELLYFISH = EntityRendererRegistry.register(HybridAquaticEntityTypes.BARREL_JELLYFISH, ::BarrelJellyfishEntityRenderer)
    val COMPASS_JELLYFISH = EntityRendererRegistry.register(HybridAquaticEntityTypes.COMPASS_JELLYFISH, ::CompassJellyfishEntityRenderer)
    val BLUE_JELLYFISH = EntityRendererRegistry.register(HybridAquaticEntityTypes.BLUE_JELLYFISH, ::BlueJellyfishEntityRenderer)
    val MAUVE_STINGER = EntityRendererRegistry.register(HybridAquaticEntityTypes.MAUVE_STINGER, ::MauveStingerEntityRenderer)
    val LIONS_MANE_JELLYFISH = EntityRendererRegistry.register(HybridAquaticEntityTypes.LIONS_MANE_JELLYFISH, ::LionsManeJellyfishEntityRenderer)
    val ATOLLA_JELLYFISH = EntityRendererRegistry.register(HybridAquaticEntityTypes.ATOLLA_JELLYFISH, ::AtollaJellyfishEntityRenderer)

    //endregion

    //region crustaceans
    val KARKINOS = EntityRendererRegistry.register(HybridAquaticEntityTypes.KARKINOS, ::KarkinosEntityRenderer)
    val DUNGENESS_CRAB = EntityRendererRegistry.register(HybridAquaticEntityTypes.DUNGENESS_CRAB, ::DungenessCrabEntityRenderer)
    val FIDDLER_CRAB = EntityRendererRegistry.register(HybridAquaticEntityTypes.FIDDLER_CRAB, ::FiddlerCrabEntityRenderer)
    val GHOST_CRAB = EntityRendererRegistry.register(HybridAquaticEntityTypes.GHOST_CRAB, ::GhostCrabEntityRenderer)
    val FLOWER_CRAB = EntityRendererRegistry.register(HybridAquaticEntityTypes.FLOWER_CRAB, ::FlowerCrabEntityRenderer)
    val LIGHTFOOT_CRAB = EntityRendererRegistry.register(HybridAquaticEntityTypes.LIGHTFOOT_CRAB, ::LightfootCrabEntityRenderer)
    val VAMPIRE_CRAB = EntityRendererRegistry.register(HybridAquaticEntityTypes.VAMPIRE_CRAB, ::VampireCrabEntityRenderer)
    val HORSESHOE_CRAB = EntityRendererRegistry.register(HybridAquaticEntityTypes.HORSESHOE_CRAB, ::HorseshoeCrabEntityRenderer)
    val SPIDER_CRAB = EntityRendererRegistry.register(HybridAquaticEntityTypes.SPIDER_CRAB, ::SpiderCrabEntityRenderer)
    val YETI_CRAB = EntityRendererRegistry.register(HybridAquaticEntityTypes.YETI_CRAB, ::YetiCrabEntityRenderer)
    val GIANT_ISOPOD = EntityRendererRegistry.register(HybridAquaticEntityTypes.GIANT_ISOPOD, ::GiantIsopodEntityRenderer)
    val SHRIMP = EntityRendererRegistry.register(HybridAquaticEntityTypes.SHRIMP, ::ShrimpEntityRenderer)
    val CRAYFISH = EntityRendererRegistry.register(HybridAquaticEntityTypes.CRAYFISH, ::CrayfishEntityRenderer)
    val LOBSTER = EntityRendererRegistry.register(HybridAquaticEntityTypes.LOBSTER, ::LobsterEntityRenderer)
    val COCONUT_CRAB = EntityRendererRegistry.register(HybridAquaticEntityTypes.COCONUT_CRAB, ::CoconutCrabEntityRenderer)
    val HERMIT_CRAB = EntityRendererRegistry.register(HybridAquaticEntityTypes.HERMIT_CRAB, ::HermitCrabEntityRenderer)

    //endregion

    //region critters
    val STARFISH = EntityRendererRegistry.register(HybridAquaticEntityTypes.STARFISH, ::StarfishEntityRenderer)
    val NUDIBRANCH = EntityRendererRegistry.register(HybridAquaticEntityTypes.NUDIBRANCH, ::NudibranchEntityRenderer)
    val SEA_CUCUMBER = EntityRendererRegistry.register(HybridAquaticEntityTypes.SEA_CUCUMBER, ::SeaCucumberEntityRenderer)
    val SEA_URCHIN = EntityRendererRegistry.register(HybridAquaticEntityTypes.SEA_URCHIN, ::SeaUrchinEntityRenderer)

    //endregion

    //region sharks
    val BULL_SHARK = EntityRendererRegistry.register(HybridAquaticEntityTypes.BULL_SHARK, ::BullSharkEntityRenderer)
    val BASKING_SHARK = EntityRendererRegistry.register(HybridAquaticEntityTypes.BASKING_SHARK, ::BaskingSharkEntityRenderer)
    val THRESHER_SHARK = EntityRendererRegistry.register(HybridAquaticEntityTypes.THRESHER_SHARK, ::ThresherSharkEntityRenderer)
    val FRILLED_SHARK = EntityRendererRegistry.register(HybridAquaticEntityTypes.FRILLED_SHARK, ::FrilledSharkEntityRenderer)
    val GREAT_WHITE_SHARK = EntityRendererRegistry.register(HybridAquaticEntityTypes.GREAT_WHITE_SHARK, ::GreatWhiteSharkEntityRenderer)
    val TIGER_SHARK = EntityRendererRegistry.register(HybridAquaticEntityTypes.TIGER_SHARK, ::TigerSharkEntityRenderer)
    val HAMMERHEAD_SHARK = EntityRendererRegistry.register(HybridAquaticEntityTypes.HAMMERHEAD_SHARK, ::HammerheadSharkEntityRenderer)
    val WHALE_SHARK = EntityRendererRegistry.register(HybridAquaticEntityTypes.WHALE_SHARK, ::WhaleSharkEntityRenderer)
    //endregion
}
