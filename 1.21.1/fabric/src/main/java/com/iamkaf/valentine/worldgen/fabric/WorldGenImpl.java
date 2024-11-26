package com.iamkaf.valentine.worldgen.fabric;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class WorldGenImpl {
    public static void addFeatureToBiome(ResourceKey<Biome> biome, GenerationStep.Decoration step,
            ResourceKey<PlacedFeature> placedKey) {
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(biome), step, placedKey);
    }
}
