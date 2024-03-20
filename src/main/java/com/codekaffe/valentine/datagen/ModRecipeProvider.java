package com.codekaffe.valentine.datagen;

import com.codekaffe.valentine.block.ModBlocks;
import com.codekaffe.valentine.item.ModItems;
import com.codekaffe.valentine.recipe.LoveyDoveyInfusingRecipeJsonBuilder;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.util.Identifier;

import java.util.function.Consumer;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {
        ShapelessRecipeJsonBuilder
                .create(RecipeCategory.DECORATIONS, Items.PURPLE_DYE)
                .input(ModBlocks.ARISTEA)
                .criterion(hasItem(ModBlocks.ARISTEA), conditionsFromItem(ModBlocks.ARISTEA))
                .offerTo(exporter, new Identifier(getRecipeName(Items.PURPLE_DYE)));

        ShapedRecipeJsonBuilder
                .create(RecipeCategory.MISC, ModBlocks.LOVEY_DOVEY_INFUSER, 1)
                .pattern("OOO")
                .pattern("XXX")
                .pattern("X X")
                .input('X', Items.IRON_INGOT)
                .input('O', ModItems.COTTON_CANDY)
                .criterion(hasItem(ModItems.COTTON_CANDY),
                        conditionsFromItem(ModItems.COTTON_CANDY)
                )
                .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(ModItems.COTTON_CANDY))
                .offerTo(exporter, new Identifier(getRecipeName(ModBlocks.LOVEY_DOVEY_INFUSER)));

        ShapedRecipeJsonBuilder
                .create(RecipeCategory.FOOD, ModItems.SPECIAL_CHOCOLATE_COOKIE, 2)
                .pattern("XXX")
                .pattern("XOX")
                .pattern("XXX")
                .input('X', ModItems.COTTON_CANDY)
                .input('O', Items.COOKIE)
                .criterion(hasItem(Items.COOKIE), conditionsFromItem(Items.COOKIE))
                .criterion(hasItem(ModItems.COTTON_CANDY),
                        conditionsFromItem(ModItems.COTTON_CANDY)
                )
                .offerTo(exporter,
                        new Identifier(getRecipeName(ModItems.SPECIAL_CHOCOLATE_COOKIE))
                );
        ShapedRecipeJsonBuilder
                .create(RecipeCategory.FOOD, ModItems.MEDIC_COOKIE, 2)
                .pattern("ABA")
                .pattern("BCB")
                .pattern("ABA")
                .input('A', Items.GLISTERING_MELON_SLICE)
                .input('B', ModItems.COTTON_CANDY)
                .input('C', Items.COOKIE)
                .criterion(hasItem(Items.GLISTERING_MELON_SLICE),
                        conditionsFromItem(Items.GLISTERING_MELON_SLICE)
                )
                .criterion(hasItem(Items.COOKIE), conditionsFromItem(Items.COOKIE))
                .criterion(hasItem(ModItems.COTTON_CANDY),
                        conditionsFromItem(ModItems.COTTON_CANDY)
                )
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.MEDIC_COOKIE)));
        ShapedRecipeJsonBuilder
                .create(RecipeCategory.FOOD, ModItems.GOOD_VISION_COOKIE, 2)
                .pattern("ABA")
                .pattern("BCB")
                .pattern("ABA")
                .input('A', Items.GOLDEN_CARROT)
                .input('B', ModItems.COTTON_CANDY)
                .input('C', Items.COOKIE)
                .criterion(hasItem(Items.GOLDEN_CARROT), conditionsFromItem(Items.GOLDEN_CARROT))
                .criterion(hasItem(Items.COOKIE), conditionsFromItem(Items.COOKIE))
                .criterion(hasItem(ModItems.COTTON_CANDY),
                        conditionsFromItem(ModItems.COTTON_CANDY)
                )
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.GOOD_VISION_COOKIE)));
        ShapedRecipeJsonBuilder
                .create(RecipeCategory.FOOD, ModItems.FIRE_COOKIE, 2)
                .pattern("ABA")
                .pattern("BCB")
                .pattern("ABA")
                .input('A', Items.MAGMA_CREAM)
                .input('B', ModItems.COTTON_CANDY)
                .input('C', Items.COOKIE)
                .criterion(hasItem(Items.MAGMA_CREAM), conditionsFromItem(Items.MAGMA_CREAM))
                .criterion(hasItem(Items.COOKIE), conditionsFromItem(Items.COOKIE))
                .criterion(hasItem(ModItems.COTTON_CANDY),
                        conditionsFromItem(ModItems.COTTON_CANDY)
                )
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.FIRE_COOKIE)));
        ShapedRecipeJsonBuilder
                .create(RecipeCategory.FOOD, ModItems.MELON_COOKIE, 2)
                .pattern("ABA")
                .pattern("BCB")
                .pattern("ABA")
                .input('A', Blocks.MELON)
                .input('B', ModItems.COTTON_CANDY)
                .input('C', Items.COOKIE)
                .criterion(hasItem(Blocks.MELON), conditionsFromItem(Blocks.MELON))
                .criterion(hasItem(Items.COOKIE), conditionsFromItem(Items.COOKIE))
                .criterion(hasItem(ModItems.COTTON_CANDY),
                        conditionsFromItem(ModItems.COTTON_CANDY)
                )
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.MELON_COOKIE)));
        ShapedRecipeJsonBuilder
                .create(RecipeCategory.FOOD, ModItems.APPLE_COOKIE, 2)
                .pattern("ABA")
                .pattern("BCB")
                .pattern("ABA")
                .input('A', Items.APPLE)
                .input('B', ModItems.COTTON_CANDY)
                .input('C', Items.COOKIE)
                .criterion(hasItem(Items.APPLE), conditionsFromItem(Items.APPLE))
                .criterion(hasItem(Items.COOKIE), conditionsFromItem(Items.COOKIE))
                .criterion(hasItem(ModItems.COTTON_CANDY),
                        conditionsFromItem(ModItems.COTTON_CANDY)
                )
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.APPLE_COOKIE)));
        ShapedRecipeJsonBuilder
                .create(RecipeCategory.FOOD, ModItems.NETHER_WART_COOKIE, 2)
                .pattern("ABA")
                .pattern("BCB")
                .pattern("ABA")
                .input('A', Items.NETHER_WART)
                .input('B', ModItems.COTTON_CANDY)
                .input('C', Items.COOKIE)
                .criterion(hasItem(Items.NETHER_WART), conditionsFromItem(Items.NETHER_WART))
                .criterion(hasItem(Items.COOKIE), conditionsFromItem(Items.COOKIE))
                .criterion(hasItem(ModItems.COTTON_CANDY),
                        conditionsFromItem(ModItems.COTTON_CANDY)
                )
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.NETHER_WART_COOKIE)));
        ShapedRecipeJsonBuilder
                .create(RecipeCategory.FOOD, ModItems.GLOW_COOKIE, 2)
                .pattern("ABA")
                .pattern("BCB")
                .pattern("ABA")
                .input('A', Items.GLOW_INK_SAC)
                .input('B', ModItems.COTTON_CANDY)
                .input('C', Items.COOKIE)
                .criterion(hasItem(Items.GLOW_INK_SAC), conditionsFromItem(Items.GLOW_INK_SAC))
                .criterion(hasItem(Items.COOKIE), conditionsFromItem(Items.COOKIE))
                .criterion(hasItem(ModItems.COTTON_CANDY),
                        conditionsFromItem(ModItems.COTTON_CANDY)
                )
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.GLOW_COOKIE)));
        ShapedRecipeJsonBuilder
                .create(RecipeCategory.FOOD, ModItems.CARAMEL_COOKIE, 2)
                .pattern("ABD")
                .pattern("BCB")
                .pattern("DBA")
                .input('A', Items.SUGAR)
                .input('D', Items.MILK_BUCKET)
                .input('B', ModItems.COTTON_CANDY)
                .input('C', Items.COOKIE)
                .criterion(hasItem(Items.SUGAR), conditionsFromItem(Items.SUGAR))
                .criterion(hasItem(Items.MILK_BUCKET), conditionsFromItem(Items.MILK_BUCKET))
                .criterion(hasItem(Items.COOKIE), conditionsFromItem(Items.COOKIE))
                .criterion(hasItem(ModItems.COTTON_CANDY),
                        conditionsFromItem(ModItems.COTTON_CANDY)
                )
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.CARAMEL_COOKIE)));
        ShapedRecipeJsonBuilder
                .create(RecipeCategory.FOOD, ModItems.EXPLOSIVE_COOKIE, 2)
                .pattern("ABA")
                .pattern("BCB")
                .pattern("ABA")
                .input('A', Blocks.TNT)
                .input('B', ModItems.COTTON_CANDY)
                .input('C', Items.COOKIE)
                .criterion(hasItem(Blocks.TNT), conditionsFromItem(Blocks.TNT))
                .criterion(hasItem(Items.COOKIE), conditionsFromItem(Items.COOKIE))
                .criterion(hasItem(ModItems.COTTON_CANDY),
                        conditionsFromItem(ModItems.COTTON_CANDY)
                )
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.EXPLOSIVE_COOKIE)));
        ShapedRecipeJsonBuilder
                .create(RecipeCategory.FOOD, ModItems.GOLDEN_COOKIE, 2)
                .pattern("ABA")
                .pattern("BCB")
                .pattern("ABA")
                .input('A', Items.GOLDEN_APPLE)
                .input('B', ModItems.COTTON_CANDY)
                .input('C', Items.COOKIE)
                .criterion(hasItem(Items.GOLDEN_APPLE), conditionsFromItem(Items.GOLDEN_APPLE))
                .criterion(hasItem(Items.COOKIE), conditionsFromItem(Items.COOKIE))
                .criterion(hasItem(ModItems.COTTON_CANDY),
                        conditionsFromItem(ModItems.COTTON_CANDY)
                )
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.GOLDEN_COOKIE)));
        ShapedRecipeJsonBuilder
                .create(RecipeCategory.FOOD, ModItems.EVIL_COOKIE, 2)
                .pattern("ABD")
                .pattern("BCB")
                .pattern("DBA")
                .input('A', Blocks.WITHER_SKELETON_SKULL)
                .input('D', Blocks.SOUL_SAND)
                .input('B', ModItems.COTTON_CANDY)
                .input('C', Items.COOKIE)
                .criterion(hasItem(Blocks.WITHER_SKELETON_SKULL),
                        conditionsFromItem(Blocks.WITHER_SKELETON_SKULL)
                )
                .criterion(hasItem(Items.MILK_BUCKET), conditionsFromItem(Items.MILK_BUCKET))
                .criterion(hasItem(Items.COOKIE), conditionsFromItem(Items.COOKIE))
                .criterion(hasItem(ModItems.COTTON_CANDY),
                        conditionsFromItem(ModItems.COTTON_CANDY)
                )
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.EVIL_COOKIE)));
        ShapedRecipeJsonBuilder
                .create(RecipeCategory.FOOD, ModItems.ARISTEA_COOKIE, 2)
                .pattern("ABA")
                .pattern("BCB")
                .pattern("ABA")
                .input('A', ModBlocks.ARISTEA)
                .input('B', ModItems.COTTON_CANDY)
                .input('C', Items.COOKIE)
                .criterion(hasItem(ModBlocks.ARISTEA), conditionsFromItem(ModBlocks.ARISTEA))
                .criterion(hasItem(Items.COOKIE), conditionsFromItem(Items.COOKIE))
                .criterion(hasItem(ModItems.COTTON_CANDY),
                        conditionsFromItem(ModItems.COTTON_CANDY)
                )
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.ARISTEA_COOKIE)));
        ShapedRecipeJsonBuilder
                .create(RecipeCategory.FOOD, ModItems.ROCKET_COOKIE, 2)
                .pattern("ABA")
                .pattern("BCB")
                .pattern("ABA")
                .input('A', Items.FIREWORK_ROCKET)
                .input('B', ModItems.COTTON_CANDY)
                .input('C', Items.COOKIE)
                .criterion(
                        hasItem(Items.FIREWORK_ROCKET),
                        conditionsFromItem(Items.FIREWORK_ROCKET)
                )
                .criterion(hasItem(Items.COOKIE), conditionsFromItem(Items.COOKIE))
                .criterion(hasItem(ModItems.COTTON_CANDY),
                        conditionsFromItem(ModItems.COTTON_CANDY)
                )
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.ROCKET_COOKIE)));
        ShapedRecipeJsonBuilder
                .create(RecipeCategory.FOOD, ModItems.SPOOKY_COOKIE, 2)
                .pattern("ABA")
                .pattern("BCB")
                .pattern("ABA")
                .input('A', Items.PUMPKIN)
                .input('B', ModItems.COTTON_CANDY)
                .input('C', Items.COOKIE)
                .criterion(hasItem(Items.PUMPKIN), conditionsFromItem(Items.PUMPKIN))
                .criterion(hasItem(Items.COOKIE), conditionsFromItem(Items.COOKIE))
                .criterion(hasItem(ModItems.COTTON_CANDY),
                        conditionsFromItem(ModItems.COTTON_CANDY)
                )
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.SPOOKY_COOKIE)));
        ShapedRecipeJsonBuilder
                .create(RecipeCategory.FOOD, ModItems.PECULIAR_COOKIE, 2)
                .pattern("ABA")
                .pattern("BCB")
                .pattern("ABA")
                .input('A', Items.PHANTOM_MEMBRANE)
                .input('B', ModItems.COTTON_CANDY)
                .input('C', Items.COOKIE)
                .criterion(hasItem(Items.PHANTOM_MEMBRANE),
                        conditionsFromItem(Items.PHANTOM_MEMBRANE)
                )
                .criterion(hasItem(Items.COOKIE), conditionsFromItem(Items.COOKIE))
                .criterion(hasItem(ModItems.COTTON_CANDY),
                        conditionsFromItem(ModItems.COTTON_CANDY)
                )
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.PECULIAR_COOKIE)));
        ShapedRecipeJsonBuilder
                .create(RecipeCategory.FOOD, ModItems.PRISMATIC_COOKIE, 2)
                .pattern("ABA")
                .pattern("BCB")
                .pattern("ABA")
                .input('A', Items.PRISMARINE)
                .input('B', ModItems.COTTON_CANDY)
                .input('C', Items.COOKIE)
                .criterion(hasItem(Items.PRISMARINE),
                        conditionsFromItem(Items.PRISMARINE)
                )
                .criterion(hasItem(Items.COOKIE), conditionsFromItem(Items.COOKIE))
                .criterion(hasItem(ModItems.COTTON_CANDY),
                        conditionsFromItem(ModItems.COTTON_CANDY)
                )
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.PRISMATIC_COOKIE)));
        ShapedRecipeJsonBuilder
                .create(RecipeCategory.FOOD, ModItems.CHORUS_COOKIE, 2)
                .pattern("ABA")
                .pattern("BCB")
                .pattern("ABA")
                .input('A', Items.CHORUS_FRUIT)
                .input('B', ModItems.COTTON_CANDY)
                .input('C', Items.COOKIE)
                .criterion(hasItem(Items.CHORUS_FRUIT),
                        conditionsFromItem(Items.CHORUS_FRUIT)
                )
                .criterion(hasItem(Items.COOKIE), conditionsFromItem(Items.COOKIE))
                .criterion(hasItem(ModItems.COTTON_CANDY),
                        conditionsFromItem(ModItems.COTTON_CANDY)
                )
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.CHORUS_COOKIE)));

        LoveyDoveyInfusingRecipeJsonBuilder
                .create()
                .input(ModItems.SPECIAL_CHOCOLATE_COOKIE)
                .output(ModItems.EXTRA_SPECIAL_CHOCOLATE_COOKIE)
                .offerTo(exporter);
        LoveyDoveyInfusingRecipeJsonBuilder
                .create()
                .input(ModItems.MEDIC_COOKIE)
                .output(ModItems.SPECIAL_MEDIC_COOKIE)
                .offerTo(exporter);
        LoveyDoveyInfusingRecipeJsonBuilder
                .create()
                .input(ModItems.GOOD_VISION_COOKIE)
                .output(ModItems.SPECIAL_GOOD_VISION_COOKIE)
                .offerTo(exporter);
        LoveyDoveyInfusingRecipeJsonBuilder
                .create()
                .input(ModItems.FIRE_COOKIE)
                .output(ModItems.SPECIAL_FIRE_COOKIE)
                .offerTo(exporter);
        LoveyDoveyInfusingRecipeJsonBuilder
                .create()
                .input(ModItems.MELON_COOKIE)
                .output(ModItems.SPECIAL_MELON_COOKIE)
                .offerTo(exporter);
        LoveyDoveyInfusingRecipeJsonBuilder
                .create()
                .input(ModItems.APPLE_COOKIE)
                .output(ModItems.SPECIAL_APPLE_COOKIE)
                .offerTo(exporter);
        LoveyDoveyInfusingRecipeJsonBuilder
                .create()
                .input(ModItems.NETHER_WART_COOKIE)
                .output(ModItems.SPECIAL_NETHER_WART_COOKIE)
                .offerTo(exporter);
        LoveyDoveyInfusingRecipeJsonBuilder
                .create()
                .input(ModItems.GLOW_COOKIE)
                .output(ModItems.SPECIAL_GLOW_COOKIE)
                .offerTo(exporter);
        LoveyDoveyInfusingRecipeJsonBuilder
                .create()
                .input(ModItems.CARAMEL_COOKIE)
                .output(ModItems.SPECIAL_CARAMEL_COOKIE)
                .offerTo(exporter);
        LoveyDoveyInfusingRecipeJsonBuilder
                .create()
                .input(ModItems.EXPLOSIVE_COOKIE)
                .output(ModItems.SPECIAL_EXPLOSIVE_COOKIE)
                .offerTo(exporter);
        LoveyDoveyInfusingRecipeJsonBuilder
                .create()
                .input(ModItems.GOLDEN_COOKIE)
                .output(ModItems.SPECIAL_GOLDEN_COOKIE)
                .offerTo(exporter);
        LoveyDoveyInfusingRecipeJsonBuilder
                .create()
                .input(ModItems.EVIL_COOKIE)
                .output(ModItems.SPECIAL_EVIL_COOKIE)
                .offerTo(exporter);
        LoveyDoveyInfusingRecipeJsonBuilder
                .create()
                .input(ModItems.ARISTEA_COOKIE)
                .output(ModItems.SPECIAL_ARISTEA_COOKIE)
                .offerTo(exporter);
        LoveyDoveyInfusingRecipeJsonBuilder
                .create()
                .input(ModItems.ROCKET_COOKIE)
                .output(ModItems.SPECIAL_ROCKET_COOKIE)
                .offerTo(exporter);
        LoveyDoveyInfusingRecipeJsonBuilder
                .create()
                .input(ModItems.SPOOKY_COOKIE)
                .output(ModItems.SPECIAL_SPOOKY_COOKIE)
                .offerTo(exporter);
        LoveyDoveyInfusingRecipeJsonBuilder
                .create()
                .input(ModItems.PECULIAR_COOKIE)
                .output(ModItems.SPECIAL_PECULIAR_COOKIE)
                .offerTo(exporter);
        LoveyDoveyInfusingRecipeJsonBuilder
                .create()
                .input(ModItems.PRISMATIC_COOKIE)
                .output(ModItems.SPECIAL_PRISMATIC_COOKIE)
                .offerTo(exporter);
        LoveyDoveyInfusingRecipeJsonBuilder
                .create()
                .input(ModItems.CHORUS_COOKIE)
                .output(ModItems.SPECIAL_CHORUS_COOKIE)
                .offerTo(exporter);
    }
}
