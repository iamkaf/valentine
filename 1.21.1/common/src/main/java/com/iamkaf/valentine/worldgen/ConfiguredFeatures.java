package com.iamkaf.valentine.worldgen;

import com.iamkaf.valentine.Valentine;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class ConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> ARISTEA_KEY = registerKey("aristea");

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, Valentine.resource(name));
    }
}
