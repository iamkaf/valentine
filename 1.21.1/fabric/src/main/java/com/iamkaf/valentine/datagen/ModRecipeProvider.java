package com.iamkaf.valentine.datagen;

import com.iamkaf.valentine.Valentine;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;


public class ModRecipeProvider extends FabricRecipeProvider {
    private static final int COOKIE_COUNT = 2; // Default cookie count for recipes

    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected @NotNull RecipeProvider createRecipeProvider(HolderLookup.Provider provider, RecipeOutput recipeOutput) {
        return new RecipeProvider(provider, recipeOutput) {
            @Override
            public void buildRecipes() {
                var itemLookup = provider.lookupOrThrow(Registries.ITEM);

                // Aristea Flower to Dye
                shapeless(RecipeCategory.DECORATIONS, Items.PURPLE_DYE).requires(Valentine.Blocks.ARISTEA.get())
                        .unlockedBy(getHasName(Valentine.Blocks.ARISTEA.get()), has(Valentine.Blocks.ARISTEA.get()))
                        .save(recipeOutput);

                // Lovey Dovey Infuser
                shaped(RecipeCategory.BUILDING_BLOCKS, Valentine.Blocks.LOVEY_DOVEY_INFUSER.get()).pattern("OOO")
                        .pattern("XXX")
                        .pattern("X X")
                        .define('X', Items.IRON_INGOT)
                        .define('O', Valentine.Items.COTTON_CANDY.get())
                        .unlockedBy(getHasName(Valentine.Items.COTTON_CANDY.get()), has(Valentine.Items.COTTON_CANDY.get()))
                        .save(recipeOutput, getSimpleRecipeName(Valentine.Blocks.LOVEY_DOVEY_INFUSER.get()));

                // Special Chocolate Cookie
                shaped(RecipeCategory.FOOD, Valentine.Items.SPECIAL_CHOCOLATE_COOKIE.get(), COOKIE_COUNT).pattern("XXX")
                        .pattern("XOX")
                        .pattern("XXX")
                        .define('X', Valentine.Items.COTTON_CANDY.get())
                        .define('O', Items.COOKIE)
                        .unlockedBy(getHasName(Items.COOKIE), has(Items.COOKIE))
                        .unlockedBy(getHasName(Valentine.Items.COTTON_CANDY.get()), has(Valentine.Items.COTTON_CANDY.get()))
                        .save(recipeOutput, getSimpleRecipeName(Valentine.Items.SPECIAL_CHOCOLATE_COOKIE.get()));

                // Medic Cookie
                shaped(RecipeCategory.FOOD, Valentine.Items.MEDIC_COOKIE.get(), COOKIE_COUNT).pattern("ABA")
                        .pattern("BCB")
                        .pattern("ABA")
                        .define('A', Items.GLISTERING_MELON_SLICE)
                        .define('B', Valentine.Items.COTTON_CANDY.get())
                        .define('C', Items.COOKIE)
                        .unlockedBy(getHasName(Items.GLISTERING_MELON_SLICE), has(Items.GLISTERING_MELON_SLICE))
                        .unlockedBy(getHasName(Items.COOKIE), has(Items.COOKIE))
                        .unlockedBy(getHasName(Valentine.Items.COTTON_CANDY.get()), has(Valentine.Items.COTTON_CANDY.get()))
                        .save(recipeOutput, getSimpleRecipeName(Valentine.Items.MEDIC_COOKIE.get()));

                // Good Vision Cookie
                shaped(RecipeCategory.FOOD, Valentine.Items.GOOD_VISION_COOKIE.get(), COOKIE_COUNT).pattern("ABA")
                        .pattern("BCB")
                        .pattern("ABA")
                        .define('A', Items.GOLDEN_CARROT)
                        .define('B', Valentine.Items.COTTON_CANDY.get())
                        .define('C', Items.COOKIE)
                        .unlockedBy(getHasName(Items.GOLDEN_CARROT), has(Items.GOLDEN_CARROT))
                        .unlockedBy(getHasName(Items.COOKIE), has(Items.COOKIE))
                        .unlockedBy(getHasName(Valentine.Items.COTTON_CANDY.get()), has(Valentine.Items.COTTON_CANDY.get()))
                        .save(recipeOutput, getSimpleRecipeName(Valentine.Items.GOOD_VISION_COOKIE.get()));

                // Fire Cookie
                shaped(RecipeCategory.FOOD, Valentine.Items.FIRE_COOKIE.get(), COOKIE_COUNT).pattern("ABA")
                        .pattern("BCB")
                        .pattern("ABA")
                        .define('A', Items.MAGMA_CREAM)
                        .define('B', Valentine.Items.COTTON_CANDY.get())
                        .define('C', Items.COOKIE)
                        .unlockedBy(getHasName(Items.MAGMA_CREAM), has(Items.MAGMA_CREAM))
                        .unlockedBy(getHasName(Items.COOKIE), has(Items.COOKIE))
                        .unlockedBy(getHasName(Valentine.Items.COTTON_CANDY.get()), has(Valentine.Items.COTTON_CANDY.get()))
                        .save(recipeOutput, getSimpleRecipeName(Valentine.Items.FIRE_COOKIE.get()));

                // Melon Cookie
                shaped(RecipeCategory.FOOD, Valentine.Items.MELON_COOKIE.get(), COOKIE_COUNT).pattern("ABA")
                        .pattern("BCB")
                        .pattern("ABA")
                        .define('A', Items.MELON_SLICE)
                        .define('B', Valentine.Items.COTTON_CANDY.get())
                        .define('C', Items.COOKIE)
                        .unlockedBy(getHasName(Items.MELON_SLICE), has(Items.MELON_SLICE))
                        .unlockedBy(getHasName(Items.COOKIE), has(Items.COOKIE))
                        .unlockedBy(getHasName(Valentine.Items.COTTON_CANDY.get()), has(Valentine.Items.COTTON_CANDY.get()))
                        .save(recipeOutput, getSimpleRecipeName(Valentine.Items.MELON_COOKIE.get()));

                // Apple Cookie
                shaped(RecipeCategory.FOOD, Valentine.Items.APPLE_COOKIE.get(), COOKIE_COUNT).pattern("ABA")
                        .pattern("BCB")
                        .pattern("ABA")
                        .define('A', Items.APPLE)
                        .define('B', Valentine.Items.COTTON_CANDY.get())
                        .define('C', Items.COOKIE)
                        .unlockedBy(getHasName(Items.APPLE), has(Items.APPLE))
                        .unlockedBy(getHasName(Items.COOKIE), has(Items.COOKIE))
                        .unlockedBy(getHasName(Valentine.Items.COTTON_CANDY.get()), has(Valentine.Items.COTTON_CANDY.get()))
                        .save(recipeOutput, getSimpleRecipeName(Valentine.Items.APPLE_COOKIE.get()));

                // Nether Wart Cookie
                shaped(RecipeCategory.FOOD, Valentine.Items.NETHER_WART_COOKIE.get(), COOKIE_COUNT).pattern("ABA")
                        .pattern("BCB")
                        .pattern("ABA")
                        .define('A', Items.NETHER_WART)
                        .define('B', Valentine.Items.COTTON_CANDY.get())
                        .define('C', Items.COOKIE)
                        .unlockedBy(getHasName(Items.NETHER_WART), has(Items.NETHER_WART))
                        .unlockedBy(getHasName(Items.COOKIE), has(Items.COOKIE))
                        .unlockedBy(getHasName(Valentine.Items.COTTON_CANDY.get()), has(Valentine.Items.COTTON_CANDY.get()))
                        .save(recipeOutput, getSimpleRecipeName(Valentine.Items.NETHER_WART_COOKIE.get()));

                // Glow Cookie
                shaped(RecipeCategory.FOOD, Valentine.Items.GLOW_COOKIE.get(), COOKIE_COUNT).pattern("ABA")
                        .pattern("BCB")
                        .pattern("ABA")
                        .define('A', Items.GLOW_INK_SAC)
                        .define('B', Valentine.Items.COTTON_CANDY.get())
                        .define('C', Items.COOKIE)
                        .unlockedBy(getHasName(Items.GLOW_INK_SAC), has(Items.GLOW_INK_SAC))
                        .unlockedBy(getHasName(Items.COOKIE), has(Items.COOKIE))
                        .unlockedBy(getHasName(Valentine.Items.COTTON_CANDY.get()), has(Valentine.Items.COTTON_CANDY.get()))
                        .save(recipeOutput, getSimpleRecipeName(Valentine.Items.GLOW_COOKIE.get()));

                // Caramel Cookie
                shaped(RecipeCategory.FOOD, Valentine.Items.CARAMEL_COOKIE.get(), COOKIE_COUNT).pattern("ABD")
                        .pattern("BCB")
                        .pattern("DBA")
                        .define('A', Items.SUGAR)
                        .define('D', Items.MILK_BUCKET)
                        .define('B', Valentine.Items.COTTON_CANDY.get())
                        .define('C', Items.COOKIE)
                        .unlockedBy(getHasName(Items.SUGAR), has(Items.SUGAR))
                        .unlockedBy(getHasName(Items.MILK_BUCKET), has(Items.MILK_BUCKET))
                        .unlockedBy(getHasName(Items.COOKIE), has(Items.COOKIE))
                        .unlockedBy(getHasName(Valentine.Items.COTTON_CANDY.get()), has(Valentine.Items.COTTON_CANDY.get()))
                        .save(recipeOutput, getSimpleRecipeName(Valentine.Items.CARAMEL_COOKIE.get()));

                // Explosive Cookie
                shaped(RecipeCategory.FOOD, Valentine.Items.EXPLOSIVE_COOKIE.get(), COOKIE_COUNT).pattern("ABA")
                        .pattern("BCB")
                        .pattern("ABA")
                        .define('A', Items.TNT)
                        .define('B', Valentine.Items.COTTON_CANDY.get())
                        .define('C', Items.COOKIE)
                        .unlockedBy(getHasName(Items.TNT), has(Items.TNT))
                        .unlockedBy(getHasName(Items.COOKIE), has(Items.COOKIE))
                        .unlockedBy(getHasName(Valentine.Items.COTTON_CANDY.get()), has(Valentine.Items.COTTON_CANDY.get()))
                        .save(recipeOutput, getSimpleRecipeName(Valentine.Items.EXPLOSIVE_COOKIE.get()));

                // Golden Cookie
                shaped(RecipeCategory.FOOD, Valentine.Items.GOLDEN_COOKIE.get(), COOKIE_COUNT).pattern("ABA")
                        .pattern("BCB")
                        .pattern("ABA")
                        .define('A', Items.GOLDEN_APPLE)
                        .define('B', Valentine.Items.COTTON_CANDY.get())
                        .define('C', Items.COOKIE)
                        .unlockedBy(getHasName(Items.GOLDEN_APPLE), has(Items.GOLDEN_APPLE))
                        .unlockedBy(getHasName(Items.COOKIE), has(Items.COOKIE))
                        .unlockedBy(getHasName(Valentine.Items.COTTON_CANDY.get()), has(Valentine.Items.COTTON_CANDY.get()))
                        .save(recipeOutput, getSimpleRecipeName(Valentine.Items.GOLDEN_COOKIE.get()));

                // Evil Cookie
                shaped(RecipeCategory.FOOD, Valentine.Items.EVIL_COOKIE.get(), COOKIE_COUNT).pattern("ABD")
                        .pattern("BCB")
                        .pattern("DBA")
                        .define('A', Items.WITHER_SKELETON_SKULL)
                        .define('D', Items.SOUL_SAND)
                        .define('B', Valentine.Items.COTTON_CANDY.get())
                        .define('C', Items.COOKIE)
                        .unlockedBy(getHasName(Items.WITHER_SKELETON_SKULL), has(Items.WITHER_SKELETON_SKULL))
                        .unlockedBy(getHasName(Items.SOUL_SAND), has(Items.SOUL_SAND))
                        .unlockedBy(getHasName(Items.COOKIE), has(Items.COOKIE))
                        .unlockedBy(getHasName(Valentine.Items.COTTON_CANDY.get()), has(Valentine.Items.COTTON_CANDY.get()))
                        .save(recipeOutput, getSimpleRecipeName(Valentine.Items.EVIL_COOKIE.get()));

                // Aristea Cookie
                shaped(RecipeCategory.FOOD, Valentine.Items.ARISTEA_COOKIE.get(), COOKIE_COUNT).pattern("ABA")
                        .pattern("BCB")
                        .pattern("ABA")
                        .define('A', Valentine.Blocks.ARISTEA.get())
                        .define('B', Valentine.Items.COTTON_CANDY.get())
                        .define('C', Items.COOKIE)
                        .unlockedBy(getHasName(Valentine.Blocks.ARISTEA.get()), has(Valentine.Blocks.ARISTEA.get()))
                        .unlockedBy(getHasName(Items.COOKIE), has(Items.COOKIE))
                        .unlockedBy(getHasName(Valentine.Items.COTTON_CANDY.get()), has(Valentine.Items.COTTON_CANDY.get()))
                        .save(recipeOutput, getSimpleRecipeName(Valentine.Items.ARISTEA_COOKIE.get()));

                // Rocket Cookie
                shaped(RecipeCategory.FOOD, Valentine.Items.ROCKET_COOKIE.get(), COOKIE_COUNT).pattern("ABA")
                        .pattern("BCB")
                        .pattern("ABA")
                        .define('A', Items.FIREWORK_ROCKET)
                        .define('B', Valentine.Items.COTTON_CANDY.get())
                        .define('C', Items.COOKIE)
                        .unlockedBy(getHasName(Items.FIREWORK_ROCKET), has(Items.FIREWORK_ROCKET))
                        .unlockedBy(getHasName(Items.COOKIE), has(Items.COOKIE))
                        .unlockedBy(getHasName(Valentine.Items.COTTON_CANDY.get()), has(Valentine.Items.COTTON_CANDY.get()))
                        .save(recipeOutput, getSimpleRecipeName(Valentine.Items.ROCKET_COOKIE.get()));

                // Spooky Cookie
                shaped(RecipeCategory.FOOD, Valentine.Items.SPOOKY_COOKIE.get(), COOKIE_COUNT).pattern("ABA")
                        .pattern("BCB")
                        .pattern("ABA")
                        .define('A', Items.PUMPKIN)
                        .define('B', Valentine.Items.COTTON_CANDY.get())
                        .define('C', Items.COOKIE)
                        .unlockedBy(getHasName(Items.PUMPKIN), has(Items.PUMPKIN))
                        .unlockedBy(getHasName(Items.COOKIE), has(Items.COOKIE))
                        .unlockedBy(getHasName(Valentine.Items.COTTON_CANDY.get()), has(Valentine.Items.COTTON_CANDY.get()))
                        .save(recipeOutput, getSimpleRecipeName(Valentine.Items.SPOOKY_COOKIE.get()));

                // Peculiar Cookie
                shaped(RecipeCategory.FOOD, Valentine.Items.PECULIAR_COOKIE.get(), COOKIE_COUNT).pattern("ABA")
                        .pattern("BCB")
                        .pattern("ABA")
                        .define('A', Items.PHANTOM_MEMBRANE)
                        .define('B', Valentine.Items.COTTON_CANDY.get())
                        .define('C', Items.COOKIE)
                        .unlockedBy(getHasName(Items.PHANTOM_MEMBRANE), has(Items.PHANTOM_MEMBRANE))
                        .unlockedBy(getHasName(Items.COOKIE), has(Items.COOKIE))
                        .unlockedBy(getHasName(Valentine.Items.COTTON_CANDY.get()), has(Valentine.Items.COTTON_CANDY.get()))
                        .save(recipeOutput, getSimpleRecipeName(Valentine.Items.PECULIAR_COOKIE.get()));

                // Prismatic Cookie
                shaped(RecipeCategory.FOOD, Valentine.Items.PRISMATIC_COOKIE.get(), COOKIE_COUNT).pattern("ABA")
                        .pattern("BCB")
                        .pattern("ABA")
                        .define('A', Items.PRISMARINE)
                        .define('B', Valentine.Items.COTTON_CANDY.get())
                        .define('C', Items.COOKIE)
                        .unlockedBy(getHasName(Items.PRISMARINE), has(Items.PRISMARINE))
                        .unlockedBy(getHasName(Items.COOKIE), has(Items.COOKIE))
                        .unlockedBy(getHasName(Valentine.Items.COTTON_CANDY.get()), has(Valentine.Items.COTTON_CANDY.get()))
                        .save(recipeOutput, getSimpleRecipeName(Valentine.Items.PRISMATIC_COOKIE.get()));

                // Chorus Cookie
                shaped(RecipeCategory.FOOD, Valentine.Items.CHORUS_COOKIE.get(), COOKIE_COUNT).pattern("ABA")
                        .pattern("BCB")
                        .pattern("ABA")
                        .define('A', Items.CHORUS_FRUIT)
                        .define('B', Valentine.Items.COTTON_CANDY.get())
                        .define('C', Items.COOKIE)
                        .unlockedBy(getHasName(Items.CHORUS_FRUIT), has(Items.CHORUS_FRUIT))
                        .unlockedBy(getHasName(Items.COOKIE), has(Items.COOKIE))
                        .unlockedBy(getHasName(Valentine.Items.COTTON_CANDY.get()), has(Valentine.Items.COTTON_CANDY.get()))
                        .save(recipeOutput, getSimpleRecipeName(Valentine.Items.CHORUS_COOKIE.get()));

                // Berry Cookie
                shaped(RecipeCategory.FOOD, Valentine.Items.BERRY_COOKIE.get(), COOKIE_COUNT).pattern("ABA")
                        .pattern("BCB")
                        .pattern("ABA")
                        .define('A', Items.SWEET_BERRIES)
                        .define('B', Valentine.Items.COTTON_CANDY.get())
                        .define('C', Items.COOKIE)
                        .unlockedBy(getHasName(Items.SWEET_BERRIES), has(Items.SWEET_BERRIES))
                        .unlockedBy(getHasName(Items.COOKIE), has(Items.COOKIE))
                        .unlockedBy(getHasName(Valentine.Items.COTTON_CANDY.get()), has(Valentine.Items.COTTON_CANDY.get()))
                        .save(recipeOutput, getSimpleRecipeName(Valentine.Items.BERRY_COOKIE.get()));

                // Omega Cookie
                shaped(RecipeCategory.FOOD, Valentine.Items.OMEGA_COOKIE.get(), COOKIE_COUNT).pattern("ABD")
                        .pattern("BCB")
                        .pattern("DBA")
                        .define('A', Items.NETHER_STAR)
                        .define('D', Items.ENCHANTED_GOLDEN_APPLE)
                        .define('B', Valentine.Items.COTTON_CANDY.get())
                        .define('C', Items.COOKIE)
                        .unlockedBy(getHasName(Items.NETHER_STAR), has(Items.NETHER_STAR))
                        .unlockedBy(getHasName(Items.ENCHANTED_GOLDEN_APPLE), has(Items.ENCHANTED_GOLDEN_APPLE))
                        .unlockedBy(getHasName(Items.COOKIE), has(Items.COOKIE))
                        .unlockedBy(getHasName(Valentine.Items.COTTON_CANDY.get()), has(Valentine.Items.COTTON_CANDY.get()))
                        .save(recipeOutput, getSimpleRecipeName(Valentine.Items.OMEGA_COOKIE.get()));
            }
        };
    }

    @Override
    public @NotNull String getName() {
        return String.format("%s Recipes", Valentine.MOD_ID);
    }
}
