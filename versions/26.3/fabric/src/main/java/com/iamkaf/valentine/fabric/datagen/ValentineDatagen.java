package com.iamkaf.valentine.fabric.datagen;

import com.google.common.hash.Hashing;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;

public final class ValentineDatagen implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator generator) {
        generator.createPack().addProvider(CookieBookRecipeProvider::new);
    }

    private record CookieBookRecipeProvider(FabricPackOutput output) implements DataProvider {
        private static final Gson JSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();

        @Override
        public CompletableFuture<?> run(CachedOutput cache) {
            var recipe = new JsonObject();
            recipe.addProperty("type", "minecraft:crafting_shapeless");
            recipe.addProperty("category", "misc");
            var ingredients = new JsonArray();
            ingredients.add("minecraft:book");
            ingredients.add("kafvalentine:cotton_candy");
            recipe.add("ingredients", ingredients);

            var components = new JsonObject();
            components.addProperty("patchouli:book", "kafvalentine:cookies");
            var result = new JsonObject();
            result.add("components", components);
            result.addProperty("count", 1);
            result.addProperty("id", "patchouli:guide_book");
            recipe.add("result", result);

            Path target = output.getOutputFolder().resolve("data/kafvalentine/recipe/cookie_book.json");
            return saveConditionalRecipe(cache, target, recipe, "patchouli");
        }

        private static CompletableFuture<?> saveConditionalRecipe(
                CachedOutput cache, Path target, JsonObject recipe, String requiredMod) {
            var conditional = recipe.deepCopy();
            var fabricCondition = new JsonObject();
            fabricCondition.addProperty("condition", "fabric:all_mods_loaded");
            var mods = new JsonArray();
            mods.add(requiredMod);
            fabricCondition.add("values", mods);
            var fabricConditions = new JsonArray();
            fabricConditions.add(fabricCondition);
            conditional.add("fabric:load_conditions", fabricConditions);

            var neoForgeCondition = new JsonObject();
            neoForgeCondition.addProperty("type", "neoforge:mod_loaded");
            neoForgeCondition.addProperty("modid", requiredMod);
            var neoForgeConditions = new JsonArray();
            neoForgeConditions.add(neoForgeCondition);
            conditional.add("neoforge:conditions", neoForgeConditions);

            var forgeCondition = new JsonObject();
            forgeCondition.addProperty("type", "forge:mod_loaded");
            forgeCondition.addProperty("modid", requiredMod);
            conditional.add("forge:condition", forgeCondition);

            // Preserve the recipe's existing key order, two-space indentation, and final newline.
            // DataProvider.saveStable sorts keys, so write the serialized bytes through the cache.
            byte[] bytes = (JSON.toJson(conditional) + "\n").getBytes(StandardCharsets.UTF_8);
            try {
                cache.writeIfNeeded(target, bytes, Hashing.sha1().hashBytes(bytes));
                return CompletableFuture.completedFuture(null);
            } catch (IOException exception) {
                return CompletableFuture.failedFuture(exception);
            }
        }

        @Override
        public String getName() {
            return "Valentine conditional recipes";
        }
    }
}
