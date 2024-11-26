package com.codekaffe.valentine.recipe;

import com.codekaffe.valentine.KafValentine;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.item.Item;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class LoveyDoveyInfusingRecipeJsonBuilder implements RecipeJsonProvider {
    private Item input = null;
    private Item output = null;

    public static LoveyDoveyInfusingRecipeJsonBuilder create() {
        return new LoveyDoveyInfusingRecipeJsonBuilder();
    }

    public void offerTo(Consumer<RecipeJsonProvider> exporter) {
        if (input == null || output == null) {
            throw new IllegalArgumentException("Input and output for infusing recipe cannot be " + "empty " + makeName());
        }
        exporter.accept(this);
    }

    public LoveyDoveyInfusingRecipeJsonBuilder input(Item input) {
        this.input = input;
        return this;
    }

    public LoveyDoveyInfusingRecipeJsonBuilder output(Item output) {
        this.output = output;
        return this;
    }

    private String makeName() {
        var inputName = Registries.ITEM.getId(input).getPath();
        var outputName = Registries.ITEM.getId(output).getPath();

        return outputName + "_from_infusing_" + inputName;
    }

    @Override
    public void serialize(JsonObject json) {
        JsonArray jsonArray = new JsonArray();
        jsonArray.add(Ingredient.ofItems(input).toJson());
        json.add("ingredients", jsonArray);

        json.add("output", Ingredient.ofItems(output).toJson());
    }

    @Override
    public Identifier getRecipeId() {
        return new Identifier(KafValentine.MOD_ID, makeName());
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return LoveyDoveyInfusingRecipe.Serializer.INSTANCE;
    }

    @Nullable
    @Override
    public JsonObject toAdvancementJson() {
        return null;
    }

    @Nullable
    @Override
    public Identifier getAdvancementId() {
        return new Identifier(KafValentine.MOD_ID, makeName());
    }
}
