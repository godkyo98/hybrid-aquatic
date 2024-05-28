package dev.hybridlabs.aquatic.data.client

import dev.hybridlabs.aquatic.block.HybridAquaticBlocks
import dev.hybridlabs.aquatic.block.SeaMessage
import dev.hybridlabs.aquatic.data.HybridAquaticDataGenerator.filterHybridAquatic
import dev.hybridlabs.aquatic.effect.HybridAquaticStatusEffects
import dev.hybridlabs.aquatic.enchantment.HybridAquaticEnchantments
import dev.hybridlabs.aquatic.entity.HybridAquaticEntityTypes
import dev.hybridlabs.aquatic.item.HybridAquaticItemGroups
import dev.hybridlabs.aquatic.item.HybridAquaticItems
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider
import net.minecraft.entity.EntityType
import net.minecraft.entity.mob.MobEntity
import net.minecraft.registry.Registries

class LanguageProvider(output: FabricDataOutput) : FabricLanguageProvider(output) {
    override fun generateTranslations(builder: TranslationBuilder) {
        // item group
        builder.add(Registries.ITEM_GROUP.getKey(HybridAquaticItemGroups.BLOCKS).orElseThrow { IllegalStateException("Item group not registered") }, "Hybrid Aquatic Blocks")
        builder.add(Registries.ITEM_GROUP.getKey(HybridAquaticItemGroups.ITEMS).orElseThrow { IllegalStateException("Item group not registered") }, "Hybrid Aquatic Items")
        builder.add(Registries.ITEM_GROUP.getKey(HybridAquaticItemGroups.SPAWN_EGGS).orElseThrow { IllegalStateException("Item group not registered") }, "Hybrid Aquatic Spawn Eggs")

        // message in a bottle
        HybridAquaticBlocks.MESSAGE_IN_A_BOTTLE.translationKey.let { key ->
            builder.add(key, "Message in a Bottle")
            builder.add("$key.jar", "Message in a Jar")
            builder.add("$key.longneck", "Message in a Longneck Bottle")
        }

        // sea messages
        mapOf(
            "the_creepers_code" to "\"The creepers have a code...",
            "poyo" to "\"I hate litterbugs.\"\n~Poyo",
            "rick_roll" to "\"Never gonna give you up!\nNever gonna let you down!\nNever gonna run around and desert you!\n-Rick Astley",
            "bold_muddy" to "\"AW MAN I DROWNED!\n~Bold Muddy",
            "kaupenjoe" to "\"It is better to sail the seven sea, than to get STDs\n-Kaupenjoe",
            "catpenjoe" to "\"If you wanna get a catgirl, you first have to become a catboy.\n-Catpenjoe",
            "fishenjoe" to "\"Give a man a fish and you'll feed him for a day,\nkill a man and you'll have one less homeless person begging you for fish.\n-Fishenjoe",
            "loss" to "\"| ||\n|| |_",
            "willowshine" to "\"Beware the fish girl\n-Willowshine",
            "warranty" to "\"We've been trying to reach you about your car's extended warranty",
            "poke" to "\"I like cheese\n-Poke",
            "one_piece" to "\"THE ONE PIECE IS REAL\n-Poke",

        ).forEach { (id, translation) -> builder.add(SeaMessage(id).translationKey, translation) }

        // entities
        generateEntities(builder)

        // blocks
        mapOf(
            HybridAquaticBlocks.BASKING_SHARK_PLUSHIE to "Basking Shark Plushie",
            HybridAquaticBlocks.BULL_SHARK_PLUSHIE to "Bull Shark Plushie",
            HybridAquaticBlocks.FRILLED_SHARK_PLUSHIE to "Frilled Shark Plushie",
            HybridAquaticBlocks.GREAT_WHITE_SHARK_PLUSHIE to "Great White Shark Plushie",
            HybridAquaticBlocks.HAMMERHEAD_SHARK_PLUSHIE to "Hammerhead Shark Plushie",
            HybridAquaticBlocks.THRESHER_SHARK_PLUSHIE to "Thresher Shark Plushie",
            HybridAquaticBlocks.TIGER_SHARK_PLUSHIE to "Tiger Shark Plushie",
            HybridAquaticBlocks.WHALE_SHARK_PLUSHIE to "Whale Shark Plushie",
            HybridAquaticBlocks.ANEMONE to "Anemone",
            HybridAquaticBlocks.TUBE_SPONGE to "Tube Sponge",
            HybridAquaticBlocks.HYBRID_CRATE to "Hybrid Crate",
            HybridAquaticBlocks.DRIFTWOOD_CRATE to "Driftwood Crate",
            HybridAquaticBlocks.OAK_CRATE to "Oak Crate",
            HybridAquaticBlocks.SPRUCE_CRATE to "Spruce Crate",
            HybridAquaticBlocks.BIRCH_CRATE to "Birch Crate",
            HybridAquaticBlocks.DARK_OAK_CRATE to "Dark Oak Crate",
            HybridAquaticBlocks.JUNGLE_CRATE to "Jungle Crate",
            HybridAquaticBlocks.ACACIA_CRATE to "Acacia Crate",
            HybridAquaticBlocks.MANGROVE_CRATE to "Mangrove Crate",
            HybridAquaticBlocks.CHERRY_CRATE to "Cherry Crate",
            HybridAquaticBlocks.BUOY to "Buoy",
            HybridAquaticBlocks.HYDROTHERMAL_VENT to "Hydrothermal Vent",
            HybridAquaticBlocks.GIANT_CLAM to "Giant Clam",
            HybridAquaticBlocks.LOPHELIA_CORAL_BLOCK to "Lophelia Coral Block",
            HybridAquaticBlocks.DEAD_LOPHELIA_CORAL_BLOCK to "Dead Lophelia Coral Block",
            HybridAquaticBlocks.LOPHELIA_CORAL to "Lophelia Coral",
            HybridAquaticBlocks.DEAD_LOPHELIA_CORAL to "Dead Lophelia Coral",
            HybridAquaticBlocks.LOPHELIA_CORAL_FAN to "Lophelia Coral Fan",
            HybridAquaticBlocks.DEAD_LOPHELIA_CORAL_FAN to "Dead Lophelia Coral Fan",
            HybridAquaticBlocks.THORN_CORAL_BLOCK to "Thorn Coral Block",
            HybridAquaticBlocks.DEAD_THORN_CORAL_BLOCK to "Dead Thorn Coral Block",
            HybridAquaticBlocks.THORN_CORAL to "Thorn Coral",
            HybridAquaticBlocks.DEAD_THORN_CORAL to "Dead Thorn Coral",
            HybridAquaticBlocks.THORN_CORAL_FAN to "Thorn Coral Fan",
            HybridAquaticBlocks.DEAD_THORN_CORAL_FAN to "Dead Thorn Coral Fan",
            HybridAquaticBlocks.GLOWSTICK to "Glowstick",
            HybridAquaticBlocks.DRIFTWOOD_LOG to "Driftwood Log",
            HybridAquaticBlocks.DRIFTWOOD_WOOD to "Driftwood Wood",
            HybridAquaticBlocks.STRIPPED_DRIFTWOOD_LOG to "Stripped Driftwood Log",
            HybridAquaticBlocks.STRIPPED_DRIFTWOOD_WOOD to "Stripped Driftwood Wood",
            HybridAquaticBlocks.DRIFTWOOD_PLANKS to "Driftwood Planks",
            HybridAquaticBlocks.DRIFTWOOD_STAIRS to "Driftwood Stairs",
            HybridAquaticBlocks.DRIFTWOOD_SLAB to "Driftwood Slab",
            HybridAquaticBlocks.DRIFTWOOD_FENCE to "Driftwood Fence",
            HybridAquaticBlocks.DRIFTWOOD_FENCE_GATE to "Driftwood Fence Gate",
            HybridAquaticBlocks.DRIFTWOOD_DOOR to "Driftwood Door",
            HybridAquaticBlocks.DRIFTWOOD_TRAPDOOR to "Driftwood Trapdoor",
            HybridAquaticBlocks.DRIFTWOOD_PRESSURE_PLATE to "Driftwood Pressure Plate",
            HybridAquaticBlocks.DRIFTWOOD_BUTTON to "Driftwood Button",
            HybridAquaticBlocks.COCONUT_PALM_LOG to "Coconut Palm Log",
            HybridAquaticBlocks.COCONUT_PALM_WOOD to "Coconut Palm Wood",
            HybridAquaticBlocks.STRIPPED_COCONUT_PALM_LOG to "Stripped Coconut Palm Log",
            HybridAquaticBlocks.STRIPPED_COCONUT_PALM_WOOD to "Stripped Coconut Palm Wood",
            HybridAquaticBlocks.COCONUT_PALM_PLANKS to "Coconut Palm Planks",
            HybridAquaticBlocks.COCONUT_PALM_STAIRS to "Coconut Palm Stairs",
            HybridAquaticBlocks.COCONUT_PALM_SLAB to "Coconut Palm Slab",
            HybridAquaticBlocks.COCONUT_PALM_FENCE to "Coconut Palm Fence",
            HybridAquaticBlocks.COCONUT_PALM_FENCE_GATE to "Coconut Palm Fence Gate",
            HybridAquaticBlocks.COCONUT_PALM_PRESSURE_PLATE to "Coconut Palm Pressure Plate",
            HybridAquaticBlocks.COCONUT_PALM_BUTTON to "Coconut Palm Button",
            HybridAquaticBlocks.COCONUT_PALM_LEAVES to "Coconut Palm Leaves",
            HybridAquaticBlocks.COCONUT_PALM_SAPLING to "Coconut Palm Sapling",
            HybridAquaticBlocks.TUBE_WORMS to "Tube Worms",
        ).forEach { (block, translation) ->
            builder.add(block, translation)
        }

        // items
        mapOf(
            HybridAquaticItems.RAW_FISH_STEAK to "Fish Steak",
            HybridAquaticItems.COOKED_FISH_STEAK to "Cooked Fish Steak",
            HybridAquaticItems.RAW_FISH_MEAT to "Raw Fish Meat",
            HybridAquaticItems.COOKED_FISH_MEAT to "Cooked Fish Meat",
            HybridAquaticItems.RAW_TENTACLE to "Raw Tentacle",
            HybridAquaticItems.COOKED_TENTACLE to "Cooked Tentacle",
            HybridAquaticItems.RAW_CRAB to "Raw Crab",
            HybridAquaticItems.COOKED_CRAB to "Cooked Crab",
            HybridAquaticItems.RAW_LOBSTER to "Raw Lobster",
            HybridAquaticItems.COOKED_LOBSTER to "Cooked Lobster",
            HybridAquaticItems.RAW_SHRIMP to "Raw Shrimp",
            HybridAquaticItems.COOKED_SHRIMP to "Cooked Shrimp",
            HybridAquaticItems.RAW_CRAYFISH to "Raw Crayfish",
            HybridAquaticItems.COOKED_CRAYFISH to "Cooked Crayfish",
            HybridAquaticItems.LIONFISH to "Lionfish",
            HybridAquaticItems.MAHI_MAHI to "Mahi Mahi",
            HybridAquaticItems.YELLOWFIN_TUNA to "Yellowfin Tuna",
            HybridAquaticItems.OPAH to "Opah",
            HybridAquaticItems.ROCKFISH to "Rockfish",
            HybridAquaticItems.BLUE_SPOTTED_STINGRAY to "Blue Spotted Stingray",
            HybridAquaticItems.MORAY_EEL to "Moray Eel",
            HybridAquaticItems.NEEDLEFISH to "Needlefish",
            HybridAquaticItems.PIRANHA to "Piranha",
            HybridAquaticItems.ANGLERFISH to "Anglerfish",
            HybridAquaticItems.BARRELEYE to "Barreleye",
            HybridAquaticItems.BLUE_TANG to "Blue Tang",
            HybridAquaticItems.CLOWNFISH to "Clownfish",
            HybridAquaticItems.UNICORN_FISH to "Unicorn Fish",
            HybridAquaticItems.TIGER_BARB to "Tiger Barb",
            HybridAquaticItems.OSCAR to "Oscar",
            HybridAquaticItems.TRIGGERFISH to "Triggerfish",
            HybridAquaticItems.COWFISH to "Cowfish",
            HybridAquaticItems.LOBSTER_CLAW to "Lobster Claw",
            HybridAquaticItems.DUNGENESS_CRAB_CLAW to "Dungeness Crab Claw",
            HybridAquaticItems.COCONUT_CRAB_CLAW to "Coconut Crab Claw",
            HybridAquaticItems.FIDDLER_CRAB_CLAW to "Fiddler Crab Claw",
            HybridAquaticItems.YETI_CRAB_CLAW to "Yeti Crab Claw",
            HybridAquaticItems.LIGHTFOOT_CRAB_CLAW to "Lightfoot Crab Claw",
            HybridAquaticItems.GHOST_CRAB_CLAW to "Ghost Crab Claw",
            HybridAquaticItems.FLOWER_CRAB_CLAW to "Flower Crab Claw",
            HybridAquaticItems.VAMPIRE_CRAB_CLAW to "Vampire Crab Claw",
            HybridAquaticItems.SPIDER_CRAB_CLAW to "Spider Crab Claw",
            HybridAquaticItems.GLOW_SLIME to "Glow Slime",
            HybridAquaticItems.SHARK_TOOTH to "Shark Tooth",
            HybridAquaticItems.SPONGE_CHUNK to "Sponge Chunk",
            HybridAquaticItems.PEARL to "Pearl",
            HybridAquaticItems.BLACK_PEARL to "Black Pearl",
            HybridAquaticItems.BARBED_HOOK to "Barbed Hook",
            HybridAquaticItems.GLOWING_HOOK to "Glowing Hook",
            HybridAquaticItems.MAGNETIC_HOOK to "Magnetic Hook",
            HybridAquaticItems.OMINOUS_HOOK to "Ominous Hook",
            HybridAquaticItems.FISHING_NET to "Fishing Net",
            HybridAquaticItems.KARKINOS_CLAW to "Karkinos Claw",
            HybridAquaticItems.DIVING_HELMET to "Diving Helmet",
            HybridAquaticItems.DIVING_SUIT to "Diving Suit",
            HybridAquaticItems.DIVING_LEGGINGS to "Diving Leggings",
            HybridAquaticItems.DIVING_BOOTS to "Diving Boots",
            HybridAquaticItems.MANGLERFISH_LURE to "Manglerfish Lure",
            HybridAquaticItems.MANGLERFISH_FIN to "Manglerfish Fin",
            HybridAquaticItems.EEL_SCARF to "Eel Scarf",
            HybridAquaticItems.TURTLE_CHESTPLATE to "Turtle Chestplate",
            HybridAquaticItems.MOON_JELLYFISH_HAT to "Moon Jellyfish Hat",
        ).forEach { (item, translation) ->
            builder.add(item, translation)
        }

        // effects
        mapOf(
            HybridAquaticStatusEffects.BLEEDING to "Bleeding",
            HybridAquaticStatusEffects.CLARITY to "Clarity",
            HybridAquaticStatusEffects.THALASSOPHOBIA to "Thalassophobia",
        ).forEach { (effect, translation) ->
            val identifier = Registries.STATUS_EFFECT.getId(effect)
            builder.add("effect.${identifier?.namespace}.${identifier?.path}", translation)
        }

        // Item descriptions
        mapOf(
            "item.hybrid-aquatic.hook" to "Needs to be put in the offhand",
            HybridAquaticItems.BARBED_HOOK.translationKey to "Increases fishing speed during the day",
            HybridAquaticItems.GLOWING_HOOK.translationKey to "Increases fishing speed at night",
            HybridAquaticItems.MAGNETIC_HOOK.translationKey to "Increases treasure chance",
            HybridAquaticItems.OMINOUS_HOOK.translationKey to "Summons Karkinos",
            HybridAquaticBlocks.HYBRID_CRATE.translationKey to "Break with an axe to open",
            HybridAquaticBlocks.DRIFTWOOD_CRATE.translationKey to "Break with an axe to open",
            HybridAquaticBlocks.OAK_CRATE.translationKey to "Break with an axe to open",
            HybridAquaticBlocks.SPRUCE_CRATE.translationKey to "Break with an axe to open",
            HybridAquaticBlocks.BIRCH_CRATE.translationKey to "Break with an axe to open",
            HybridAquaticBlocks.DARK_OAK_CRATE.translationKey to "Break with an axe to open",
            HybridAquaticBlocks.JUNGLE_CRATE.translationKey to "Break with an axe to open",
            HybridAquaticBlocks.MANGROVE_CRATE.translationKey to "Break with an axe to open",
            HybridAquaticBlocks.ACACIA_CRATE.translationKey to "Break with an axe to open",
            HybridAquaticBlocks.CHERRY_CRATE.translationKey to "Break with an axe to open",
            HybridAquaticItems.FISHING_NET.translationKey to "Stored Entity: %s",
            HybridAquaticItems.MOON_JELLYFISH_HAT.translationKey to "Made by Jakotens",
        ).forEach { (itemTranslationKey, translation) ->
            builder.add(itemTranslationKey.plus(".description"), translation)
        }

        // enchantments
        mapOf(
            HybridAquaticEnchantments.LIVECATCH to "Live Catch",
        ).forEach { (enchantment, translation) ->
            builder.add(enchantment, translation)
        }

        mapOf(
            "glowing" to "Glowing",
            "clarity" to "Clarity",
            "thalassophobia" to "Thalassophobia",
            "bleeding" to "Bleeding"
        ).forEach { (potion, translation) ->
            builder.add("item.minecraft.potion.effect.$potion", "Potion of $translation")
            builder.add("item.minecraft.splash_potion.effect.$potion", "Splash Potion of $translation")
            builder.add("item.minecraft.lingering_potion.effect.$potion", "Lingering Potion of $translation")
            builder.add("item.minecraft.tipped_arrow.effect.$potion", "Arrow of $translation")
        }
    }

    private fun generateEntities(builder: TranslationBuilder) {
        // create map of entities to their display names
        val entityNameMap = mapOf(
            HybridAquaticEntityTypes.CLOWNFISH to "Clownfish",
            HybridAquaticEntityTypes.ANGLERFISH to "Anglerfish",
            HybridAquaticEntityTypes.DRAGONFISH to "Dragonfish",
            HybridAquaticEntityTypes.BARRELEYE to "Barreleye",
            HybridAquaticEntityTypes.YELLOWFIN_TUNA to "Yellowfin Tuna",
            HybridAquaticEntityTypes.CUTTLEFISH to "Cuttlefish",
            HybridAquaticEntityTypes.FLASHLIGHT_FISH to "Flashlight Fish",
            HybridAquaticEntityTypes.LIONFISH to "Lionfish",
            HybridAquaticEntityTypes.OARFISH to "Oarfish",
            HybridAquaticEntityTypes.OPAH to "Opah",
            HybridAquaticEntityTypes.PIRANHA to "Piranha",
            HybridAquaticEntityTypes.SEA_ANGEL to "Sea Angel",
            HybridAquaticEntityTypes.SUNFISH to "Sunfish",
            HybridAquaticEntityTypes.VAMPIRE_SQUID to "Vampire Squid",
            HybridAquaticEntityTypes.MAHIMAHI to "Mahi Mahi",
            HybridAquaticEntityTypes.MORAY_EEL to "Moray Eel",
            HybridAquaticEntityTypes.ROCKFISH to "Rockfish",
            HybridAquaticEntityTypes.TIGER_BARB to "Tiger Barb",
            HybridAquaticEntityTypes.NEEDLEFISH to "Needlefish",
            HybridAquaticEntityTypes.RATFISH to "Ratfish",
            HybridAquaticEntityTypes.NAUTILUS to "Nautilus",
            HybridAquaticEntityTypes.UMBRELLA_OCTOPUS to "Umbrella Octopus",
            HybridAquaticEntityTypes.TRIGGERFISH to "Triggerfish",
            HybridAquaticEntityTypes.OSCAR to "Oscar",
            HybridAquaticEntityTypes.UNICORN_FISH to "Unicorn Fish",
            HybridAquaticEntityTypes.ZEBRA_DANIO to "Zebra Danio",
            HybridAquaticEntityTypes.TOADFISH to "Toadfish",
            HybridAquaticEntityTypes.TETRA to "Tetra",
            HybridAquaticEntityTypes.STONEFISH to "Stonefish",
            HybridAquaticEntityTypes.BETTA to "Betta",
            HybridAquaticEntityTypes.SEAHORSE to "Seahorse",
            HybridAquaticEntityTypes.MOON_JELLYFISH to "Moon Jellyfish",
            HybridAquaticEntityTypes.GOURAMI to "Gourami",
            HybridAquaticEntityTypes.COWFISH to "Cowfish",
            HybridAquaticEntityTypes.GLOWING_SUCKER_OCTOPUS to "Glowing Sucker Octopus",
            HybridAquaticEntityTypes.DISCUS to "Discus",
            HybridAquaticEntityTypes.FIREFLY_SQUID to "Firefly Squid",
            HybridAquaticEntityTypes.BLUE_SPOTTED_STINGRAY to "Blue Spotted Stingray",
            HybridAquaticEntityTypes.BLUE_TANG to "Blue Tang",
            HybridAquaticEntityTypes.BULL_SHARK to "Bull Shark",
            HybridAquaticEntityTypes.BASKING_SHARK to "Basking Shark",
            HybridAquaticEntityTypes.THRESHER_SHARK to "Thresher Shark",
            HybridAquaticEntityTypes.FRILLED_SHARK to "Frilled Shark",
            HybridAquaticEntityTypes.GREAT_WHITE_SHARK to "Great White Shark",
            HybridAquaticEntityTypes.TIGER_SHARK to "Tiger Shark",
            HybridAquaticEntityTypes.HAMMERHEAD_SHARK to "Hammerhead Shark",
            HybridAquaticEntityTypes.WHALE_SHARK to "Whale Shark",
            HybridAquaticEntityTypes.KARKINOS to "Karkinos",
            HybridAquaticEntityTypes.DUNGENESS_CRAB to "Crab",
            HybridAquaticEntityTypes.FIDDLER_CRAB to "Fiddler Crab",
            HybridAquaticEntityTypes.HERMIT_CRAB to "Hermit Crab",
            HybridAquaticEntityTypes.GHOST_CRAB to "Ghost Crab",
            HybridAquaticEntityTypes.LIGHTFOOT_CRAB to "Lightfoot Crab",
            HybridAquaticEntityTypes.FLOWER_CRAB to "Flower Crab",
            HybridAquaticEntityTypes.VAMPIRE_CRAB to "Vampire Crab",
            HybridAquaticEntityTypes.SPIDER_CRAB to "Spider Crab",
            HybridAquaticEntityTypes.YETI_CRAB to "Yeti Crab",
            HybridAquaticEntityTypes.COCONUT_CRAB to "Coconut Crab",
            HybridAquaticEntityTypes.HORSESHOE_CRAB to "Horseshoe Crab",
            HybridAquaticEntityTypes.GIANT_ISOPOD to "Giant Isopod",
            HybridAquaticEntityTypes.SHRIMP to "Shrimp",
            HybridAquaticEntityTypes.CRAYFISH to "Crayfish",
            HybridAquaticEntityTypes.LOBSTER to "Lobster",
            HybridAquaticEntityTypes.NUDIBRANCH to "Nudibranch",
            HybridAquaticEntityTypes.SEA_CUCUMBER to "Sea Cucumber",
            HybridAquaticEntityTypes.SEA_URCHIN to "Sea Urchin",
            HybridAquaticEntityTypes.STARFISH to "Starfish",
            HybridAquaticEntityTypes.SEA_NETTLE to "Sea Nettle",
            HybridAquaticEntityTypes.FRIED_EGG_JELLYFISH to "Fried Egg Jellyfish",
            HybridAquaticEntityTypes.CAULIFLOWER_JELLYFISH to "Cauliflower Jellyfish",
            HybridAquaticEntityTypes.NOMURA_JELLYFISH to "Nomura Jellyfish",
            HybridAquaticEntityTypes.BARREL_JELLYFISH to "Barrel Jellyfish",
            HybridAquaticEntityTypes.COMPASS_JELLYFISH to "Compass Jellyfish",
            HybridAquaticEntityTypes.MAUVE_STINGER to "Mauve Stinger",
            HybridAquaticEntityTypes.LIONS_MANE_JELLYFISH to "Lion's Mane Jellyfish",
            HybridAquaticEntityTypes.ATOLLA_JELLYFISH to "Atolla Jellyfish",
            HybridAquaticEntityTypes.BLUE_JELLYFISH to "Blue Jellyfish",
        )

        // verify display name list is valid
        val nonPresentEntityNames = mutableListOf<EntityType<*>>()

        Registries.ENTITY_TYPE
            .filter(filterHybridAquatic(Registries.ENTITY_TYPE))
            .forEach { type ->
                if (type.baseClass.isAssignableFrom(MobEntity::class.java)) {
                    if (!entityNameMap.containsKey(type)) {
                        nonPresentEntityNames.add(type)
                    }
                }
            }

        if (nonPresentEntityNames.isNotEmpty()) {
            throw throw IllegalStateException("Entity to display name map does not contain ${nonPresentEntityNames.joinToString()}. Please modify ${javaClass.simpleName} accordingly.")
        }

        // generate entity and entity spawn egg translations
        entityNameMap.forEach { (entityType, translation) ->
            val id = Registries.ENTITY_TYPE.getId(entityType)
            val translationKey = entityType.translationKey
            val namespace = id.namespace
            val path = id.path
            builder.add(translationKey, translation)
            builder.add("item.$namespace.${path}_spawn_egg", "$translation Spawn Egg")
        }
    }
}
