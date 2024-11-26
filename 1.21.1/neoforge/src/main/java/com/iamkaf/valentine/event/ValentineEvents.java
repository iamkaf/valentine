package com.iamkaf.valentine.event;

import com.iamkaf.valentine.Valentine;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;

@EventBusSubscriber(modid = Valentine.MOD_ID, bus = EventBusSubscriber.Bus.GAME)
public class ValentineEvents {
    @SubscribeEvent
    public static void onBrewingRecipeRegister(RegisterBrewingRecipesEvent event) {
        PotionBrewing.Builder builder = event.getBuilder();

        builder.addMix(
                Potions.AWKWARD,
                Valentine.Blocks.ARISTEA.get().asItem(),
                Valentine.Potions.SMITTEN_POTION
        );
    }
}
