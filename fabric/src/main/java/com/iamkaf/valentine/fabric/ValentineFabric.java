package com.iamkaf.valentine.fabric;

import com.iamkaf.valentine.Valentine;
import com.iamkaf.valentine.Register;
import com.iamkaf.valentine.block.CottonCandyCropBlock;
import com.iamkaf.valentine.item.custom.Love;
import com.iamkaf.valentine.worldgen.WorldGen;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
//? if >=1.20.5 <26.3
import net.fabricmc.fabric.api.registry.FabricPotionBrewingBuilder;
//? if <1.20.5
/*import net.fabricmc.fabric.api.registry.FabricBrewingRecipeRegistry;*/
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.alchemy.Potions;

public final class ValentineFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        Valentine.init();
        Love.registerDispenseBehavior();
        //? if >=1.20.3 <26.3
        Registry.register(BuiltInRegistries.BLOCK_TYPE, Valentine.resource("cotton_candy_crop"), CottonCandyCropBlock.CODEC);
        //? if <26.3
        registerPotionRecipes();
        Register.registerCompostables();
        registerWorldgen();
    }

    //? if <26.3 {
    private void registerPotionRecipes() {
        //? if >=1.20.5 {
        FabricPotionBrewingBuilder.BUILD.register(builder -> {
            builder.registerPotionRecipe(
                    Potions.AWKWARD,
                    net.minecraft.world.item.crafting.Ingredient.of(Valentine.Blocks.ARISTEA.get().asItem()),
                    BuiltInRegistries.POTION.wrapAsHolder(Valentine.Potions.SMITTEN_POTION.get())
            );
        });
        //?} else {
        /*FabricBrewingRecipeRegistry.registerPotionRecipe(
                Potions.AWKWARD,
                net.minecraft.world.item.crafting.Ingredient.of(Valentine.Blocks.ARISTEA.get().asItem()),
                Valentine.Potions.SMITTEN_POTION.get()
        );
        *///?}
    }

    //?}

    private void registerWorldgen() {
        for (var biome : WorldGen.ARISTEA_BIOMES) {
            BiomeModifications.addFeature(BiomeSelectors.includeByKey(biome), WorldGen.ARISTEA_STEP, WorldGen.ARISTEA_PLACED_KEY);
        }
    }
}
