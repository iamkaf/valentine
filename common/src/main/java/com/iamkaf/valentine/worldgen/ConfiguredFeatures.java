package com.iamkaf.valentine.worldgen;

import com.iamkaf.valentine.Valentine;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
//? if >=26.3
/*import net.minecraft.world.level.levelgen.feature.Feature;*/
//? if <26.3
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class ConfiguredFeatures {
    //? if >=26.3
    /*public static final ResourceKey<Feature> ARISTEA_KEY = registerKey("aristea");*/
    //? if <26.3
    public static final ResourceKey<ConfiguredFeature<?, ?>> ARISTEA_KEY = registerKey("aristea");

    //? if >=26.3 {
    /*public static ResourceKey<Feature> registerKey(String name) {
        return ResourceKey.create(Registries.FEATURE, Valentine.resource(name));
    }
    *///?} else {
    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, Valentine.resource(name));
    }
    //?}
}
