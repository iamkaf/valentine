package com.iamkaf.valentine.worldgen;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.List;

public class WorldGen {
    public static void init() {
        var aristeaBiomes = List.of(
                Biomes.PLAINS,
                Biomes.SNOWY_PLAINS,
                Biomes.FLOWER_FOREST,
                Biomes.FOREST,
                Biomes.BIRCH_FOREST,
                Biomes.DARK_FOREST,
                Biomes.OLD_GROWTH_BIRCH_FOREST,
                Biomes.WINDSWEPT_FOREST,
                Biomes.MEADOW,
                Biomes.OLD_GROWTH_PINE_TAIGA,
                Biomes.OLD_GROWTH_SPRUCE_TAIGA,
                Biomes.TAIGA,
                Biomes.SNOWY_TAIGA
        );

        for (var biome : aristeaBiomes) {
            var step = GenerationStep.Decoration.VEGETAL_DECORATION;
            var placedKey = PlacedFeatures.ARISTEA_PLACED_KEY;
            addFeatureToBiome(biome, step, placedKey);
        }
    }

    @ExpectPlatform
    private static void addFeatureToBiome(ResourceKey<Biome> biome, GenerationStep.Decoration step,
            ResourceKey<PlacedFeature> placedKey) {
    }
}
