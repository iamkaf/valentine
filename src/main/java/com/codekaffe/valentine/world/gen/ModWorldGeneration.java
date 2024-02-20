package com.codekaffe.valentine.world.gen;

import com.codekaffe.valentine.world.ModPlacedFeatures;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;

public class ModWorldGeneration {
    public static void generateModWorldGen() {
        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(BiomeKeys.PLAINS),
                GenerationStep.Feature.VEGETAL_DECORATION,
                ModPlacedFeatures.ARISTEA_PLACED_KEY
        );
        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(BiomeKeys.SNOWY_PLAINS),
                GenerationStep.Feature.VEGETAL_DECORATION,
                ModPlacedFeatures.ARISTEA_PLACED_KEY
        );
        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(BiomeKeys.FLOWER_FOREST),
                GenerationStep.Feature.VEGETAL_DECORATION,
                ModPlacedFeatures.ARISTEA_PLACED_KEY
        );
        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(BiomeKeys.FOREST),
                GenerationStep.Feature.VEGETAL_DECORATION,
                ModPlacedFeatures.ARISTEA_PLACED_KEY
        );
        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(BiomeKeys.BIRCH_FOREST),
                GenerationStep.Feature.VEGETAL_DECORATION,
                ModPlacedFeatures.ARISTEA_PLACED_KEY
        );
        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(BiomeKeys.DARK_FOREST),
                GenerationStep.Feature.VEGETAL_DECORATION,
                ModPlacedFeatures.ARISTEA_PLACED_KEY
        );
        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(BiomeKeys.OLD_GROWTH_BIRCH_FOREST),
                GenerationStep.Feature.VEGETAL_DECORATION,
                ModPlacedFeatures.ARISTEA_PLACED_KEY
        );
        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(BiomeKeys.WINDSWEPT_FOREST),
                GenerationStep.Feature.VEGETAL_DECORATION,
                ModPlacedFeatures.ARISTEA_PLACED_KEY
        );
        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(BiomeKeys.MEADOW),
                GenerationStep.Feature.VEGETAL_DECORATION,
                ModPlacedFeatures.ARISTEA_PLACED_KEY
        );
    }
}
