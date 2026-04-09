package com.iamkaf.valentine.fabric;

import com.iamkaf.valentine.Valentine;
import com.iamkaf.valentine.Register;
import com.iamkaf.valentine.block.CottonCandyCropBlock;
import com.iamkaf.valentine.worldgen.WorldGen;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.registry.CompostableRegistry;
import net.fabricmc.fabric.api.registry.FabricPotionBrewingBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.alchemy.Potions;

public final class ValentineFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        Valentine.init();
        Registry.register(BuiltInRegistries.BLOCK_TYPE, Valentine.resource("cotton_candy_crop"), CottonCandyCropBlock.CODEC);
        registerPotionRecipes();
        registerCompostables();
        registerWorldgen();
    }

    private void registerPotionRecipes() {
        FabricPotionBrewingBuilder.BUILD.register(builder -> {
            builder.registerPotionRecipe(
                    Potions.AWKWARD,
                    net.minecraft.world.item.crafting.Ingredient.of(Valentine.Blocks.ARISTEA.get().asItem()),
                    BuiltInRegistries.POTION.wrapAsHolder(Valentine.Potions.SMITTEN_POTION.get())
            );
        });
    }

    private void registerCompostables() {
        Register.compostables().forEach((item, chance) -> CompostableRegistry.INSTANCE.add(item.get(), chance));
    }

    private void registerWorldgen() {
        for (var biome : WorldGen.ARISTEA_BIOMES) {
            BiomeModifications.addFeature(BiomeSelectors.includeByKey(biome), WorldGen.ARISTEA_STEP, WorldGen.ARISTEA_PLACED_KEY);
        }
    }
}
