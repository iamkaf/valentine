package com.iamkaf.valentine.fabric;

import com.iamkaf.valentine.Valentine;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.registry.FabricBrewingRecipeRegistryBuilder;
import net.minecraft.world.item.alchemy.Potions;

public final class ValentineFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        // Run our common setup.
        Valentine.init();

        registerPotionRecipes();
    }

    private void registerPotionRecipes() {
        FabricBrewingRecipeRegistryBuilder.BUILD.register(builder -> {
            builder.addMix(
                    Potions.AWKWARD,
                    Valentine.Blocks.ARISTEA.get().asItem(),
                    Valentine.Potions.SMITTEN_POTION
            );
        });
    }
}
