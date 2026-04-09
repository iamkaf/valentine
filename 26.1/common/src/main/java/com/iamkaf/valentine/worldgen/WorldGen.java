package com.iamkaf.valentine.worldgen;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.List;

public class WorldGen {
    public static final List<ResourceKey<Biome>> ARISTEA_BIOMES = List.of(
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
    public static final GenerationStep.Decoration ARISTEA_STEP = GenerationStep.Decoration.VEGETAL_DECORATION;
    public static final ResourceKey<PlacedFeature> ARISTEA_PLACED_KEY = PlacedFeatures.ARISTEA_PLACED_KEY;

    private WorldGen() {}
}
