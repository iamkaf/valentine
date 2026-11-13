// Brewing uses the shared recipe data on 26.3 and newer.
//? if <26.3 {
package com.iamkaf.valentine.forge;

import com.iamkaf.valentine.Valentine;
import net.minecraft.world.item.alchemy.Potions;
//? if >=1.20.5 {
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraftforge.event.brewing.BrewingRecipeRegisterEvent;
//?} else {
/*import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.common.crafting.StrictNBTIngredient;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import java.util.List;
*///?}

public final class ValentineForgeEvents {
    private ValentineForgeEvents() {
    }

    //? if >=1.20.5 {
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
    //?} else {
    /*public static void init(IEventBus modBus) {
        modBus.addListener((FMLCommonSetupEvent event) -> event.enqueueWork(ValentineForgeEvents::registerBrewing));
    }

    // Forge brews item stacks here, so each potion container gets the recipe a vanilla mix would give it.
    private static void registerBrewing() {
        Ingredient aristea = Ingredient.of(Valentine.Blocks.ARISTEA.get());
        for (Item container : List.of(Items.POTION, Items.SPLASH_POTION, Items.LINGERING_POTION)) {
            BrewingRecipeRegistry.addRecipe(
                    StrictNBTIngredient.of(potion(container, Potions.AWKWARD)),
                    aristea,
                    potion(container, Valentine.Potions.SMITTEN_POTION.get())
            );
        }
    }

    private static ItemStack potion(Item container, Potion potion) {
        return PotionUtils.setPotion(new ItemStack(container), potion);
    }
    *///?}
}
//?}
