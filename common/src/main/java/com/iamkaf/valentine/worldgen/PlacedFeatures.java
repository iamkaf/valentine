package com.iamkaf.valentine.worldgen;

import com.iamkaf.valentine.Valentine;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class PlacedFeatures {
    public static final ResourceKey<PlacedFeature> ARISTEA_PLACED_KEY = registerKey("aristea_placed");

    public static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, Valentine.resource(name));
    }
}
