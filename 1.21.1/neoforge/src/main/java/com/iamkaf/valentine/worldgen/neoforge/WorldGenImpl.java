package com.iamkaf.valentine.worldgen.neoforge;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class WorldGenImpl {
    public static void addFeatureToBiome(ResourceKey<Biome> biome, GenerationStep.Decoration step,
            ResourceKey<PlacedFeature> placedKey) {
        // fabric code:
//        BiomeModifications.addFeature(BiomeSelectors.includeByKey(biome), step, placedKey);
        // TODO: find out how to do this in neoforge
    }
}
