package com.codekaffe.valentine.recipe;

import com.codekaffe.valentine.KafValentine;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModRecipes {
    public static void registerRecipes() {
        Registry.register(Registries.RECIPE_SERIALIZER,
                new Identifier(KafValentine.MOD_ID, LoveyDoveyInfusingRecipe.Serializer.ID),
                LoveyDoveyInfusingRecipe.Serializer.INSTANCE
        );
        Registry.register(Registries.RECIPE_TYPE,
                new Identifier(KafValentine.MOD_ID, LoveyDoveyInfusingRecipe.Type.ID),
                LoveyDoveyInfusingRecipe.Type.INSTANCE
        );
    }
}