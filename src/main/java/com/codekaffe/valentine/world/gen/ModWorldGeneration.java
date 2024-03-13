package com.codekaffe.valentine.world.gen;

import com.codekaffe.valentine.world.ModPlacedFeatures;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;

import java.util.List;

public class ModWorldGeneration {
    public static void generateModWorldGen() {
        var aristeaBiomes = List.of(
                BiomeKeys.PLAINS,
                BiomeKeys.SNOWY_PLAINS,
                BiomeKeys.FLOWER_FOREST,
                BiomeKeys.FOREST,
                BiomeKeys.BIRCH_FOREST,
                BiomeKeys.DARK_FOREST,
                BiomeKeys.OLD_GROWTH_BIRCH_FOREST,
                BiomeKeys.WINDSWEPT_FOREST,
                BiomeKeys.MEADOW,
                BiomeKeys.OLD_GROWTH_PINE_TAIGA,
                BiomeKeys.OLD_GROWTH_SPRUCE_TAIGA,
                BiomeKeys.TAIGA,
                BiomeKeys.SNOWY_TAIGA
        );

        for (var biomeKey : aristeaBiomes) {
            BiomeModifications.addFeature(
                    BiomeSelectors.includeByKey(biomeKey),
                    GenerationStep.Feature.VEGETAL_DECORATION,
                    ModPlacedFeatures.ARISTEA_PLACED_KEY
            );
        }

    }
}
