package com.iamkaf.valentine.event;

import com.iamkaf.valentine.Valentine;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;

@EventBusSubscriber(modid = Valentine.MOD_ID)
public class ValentineEvents {
    @SubscribeEvent
    public static void onBrewingRecipeRegister(RegisterBrewingRecipesEvent event) {
        PotionBrewing.Builder builder = event.getBuilder();

        builder.addMix(Potions.AWKWARD,
                Valentine.Blocks.ARISTEA.get().asItem(),
                BuiltInRegistries.POTION.wrapAsHolder(Valentine.Potions.SMITTEN_POTION.get())
        );
    }
}
