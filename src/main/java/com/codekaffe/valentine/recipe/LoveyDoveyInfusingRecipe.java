package com.codekaffe.valentine.recipe;

import com.codekaffe.valentine.KafValentine;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;

import java.util.List;

public class LoveyDoveyInfusingRecipe implements Recipe<SimpleInventory> {
    private final ItemStack output;
    private final List<Ingredient> recipeItems;

    public LoveyDoveyInfusingRecipe(List<Ingredient> ingredients, ItemStack itemStack) {
        this.output = itemStack;
        this.recipeItems = ingredients;
    }

    @Override
    public boolean matches(SimpleInventory inventory, World world) {
        if (world.isClient()) {
            return false;
        }

        return recipeItems.get(0).test(inventory.getStack(0));
    }

    @Override
    public ItemStack craft(SimpleInventory inventory, DynamicRegistryManager registryManager) {
        return output;
    }

    @Override
    public boolean fits(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getOutput(DynamicRegistryManager registryManager) {
        return output;
    }

    @Override
    public DefaultedList<Ingredient> getIngredients() {
        DefaultedList<Ingredient> list = DefaultedList.ofSize(this.recipeItems.size());
        list.addAll(recipeItems);
        return list;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    @Override
    public Identifier getId() {
        return new Identifier(KafValentine.MOD_ID, "lovey_dovey_infusing");
    }

    public static class Type implements RecipeType<LoveyDoveyInfusingRecipe> {
        public static final Type INSTANCE = new Type();
        public static final String ID = "lovey_dovey_infusing";
    }

    public static class Serializer implements RecipeSerializer<LoveyDoveyInfusingRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final String ID = "lovey_dovey_infusing";

        private static DefaultedList<Ingredient> deserializeIngredients(JsonArray json) {
            DefaultedList<Ingredient> ingredients = DefaultedList.ofSize(json.size(),
                    Ingredient.EMPTY
            );
            for (int i = 0; i < json.size(); i++) {
                Ingredient ingredient = Ingredient.fromJson(json.get(i));
                if (!ingredient.isEmpty()) {
                    ingredients.set(i, ingredient);
                }
            }
            return ingredients;
        }

        @Override
        public LoveyDoveyInfusingRecipe read(Identifier id, JsonObject json) {
//            final DefaultedList<Ingredient> ingredients =
//                    CandlelightGeneralUtil.deserializeIngredients(JsonHelper.getArray(json,
//                    "ingredients"
//            ));
            DefaultedList<Ingredient> ingredients = deserializeIngredients(JsonHelper.getArray(json,
                    "ingredients"
            ));
            if (ingredients.isEmpty()) {
                throw new JsonParseException("No ingredients for LoveyDoveyInfuser Recipe");
            } else if (ingredients.size() > 1) {
                throw new JsonParseException("Too many ingredients for LoveyDoveyInfuser Recipe");
            } else {
                KafValentine.LOGGER.info(String.valueOf(json));
                return new LoveyDoveyInfusingRecipe(ingredients,
                        Ingredient
                                .fromJson(JsonHelper.getObject(json, "output"))
                                .getMatchingStacks()[0]
                );
            }
        }

        @Override
        public LoveyDoveyInfusingRecipe read(Identifier id, PacketByteBuf buf) {
            DefaultedList<Ingredient> inputs = DefaultedList.ofSize(buf.readInt(),
                    Ingredient.EMPTY
            );

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromPacket(buf));
            }

            ItemStack output = buf.readItemStack();
            return new LoveyDoveyInfusingRecipe(inputs, output);
        }

        @Override
        public void write(PacketByteBuf buf, LoveyDoveyInfusingRecipe recipe) {
            buf.writeInt(recipe.getIngredients().size());

            for (Ingredient ingredient : recipe.getIngredients()) {
                ingredient.write(buf);
            }

            buf.writeItemStack(recipe.getOutput(null));
        }
    }
}