package com.codekaffe.valentine.compat;

import com.codekaffe.valentine.block.ModBlocks;
import com.codekaffe.valentine.recipe.LoveyDoveyInfusingRecipe;
import com.codekaffe.valentine.screen.LoveyDoveyInfusingScreen;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.client.registry.screen.ScreenRegistry;
import me.shedaniel.rei.api.common.util.EntryStacks;

public class KafValentineREIClientPlugin implements REIClientPlugin {
    @Override
    public void registerCategories(CategoryRegistry registry) {
        registry.add(new LoveyDoveyInfusingCategory());

        registry.addWorkstations(LoveyDoveyInfusingCategory.LOVEY_DOVEY_INFUSING,
                EntryStacks.of(ModBlocks.LOVEY_DOVEY_INFUSER)
        );
    }

    @Override
    public void registerDisplays(DisplayRegistry registry) {
        registry.registerRecipeFiller(LoveyDoveyInfusingRecipe.class,
                LoveyDoveyInfusingRecipe.Type.INSTANCE,
                LoveyDoveyInfusingDisplay::new
        );
    }

    @Override
    public void registerScreens(ScreenRegistry registry) {
        registry.registerClickArea(screen -> new Rectangle(75, 30, 20, 30),
                LoveyDoveyInfusingScreen.class,
                LoveyDoveyInfusingCategory.LOVEY_DOVEY_INFUSING
        );
    }
}
