package com.iamkaf.valentine.forge;

import com.iamkaf.valentine.Valentine;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraftforge.event.brewing.BrewingRecipeRegisterEvent;

public final class ValentineForgeEvents {
    private ValentineForgeEvents() {
    }

    public static void init() {
        BrewingRecipeRegisterEvent.BUS.addListener(ValentineForgeEvents::onBrewingRecipeRegister);
    }

    private static void onBrewingRecipeRegister(BrewingRecipeRegisterEvent event) {
        PotionBrewing.Builder builder = event.getBuilder();
        builder.addMix(
                Potions.AWKWARD,
                Valentine.Blocks.ARISTEA.get().asItem(),
                BuiltInRegistries.POTION.wrapAsHolder(Valentine.Potions.SMITTEN_POTION.get())
        );
    }
}
