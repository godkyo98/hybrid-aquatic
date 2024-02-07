package dev.hybridlabs.aquatic.utils;

import net.minecraft.entity.SpawnGroup;

public enum HybridAquaticSpawnGroup {
    FISH("ha_fish", 5, true, false, 64),
    FISH_UNDERGROUND("ha_fish_underground", 5, true, false, 64),
    JELLY("ha_jelly", 5, false, false, 64),
    SHARK("ha_shark", 5, true, true, 128),
    CRITTER("ha_critter", 5, true, false, 64);

    public SpawnGroup spawnGroup;
    public final String name;
    public final int spawnCap;
    public final boolean peaceful;
    public final boolean rare;
    public final int immediateDespawnRange;

    HybridAquaticSpawnGroup(String name, int spawnCap, boolean peaceful, boolean rare, int immediateDespawnRange) {
        this.name = name;
        this.spawnCap = spawnCap;
        this.peaceful = peaceful;
        this.rare = rare;
        this.immediateDespawnRange = immediateDespawnRange;
    }
}
