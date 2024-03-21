package com.codekaffe.valentine;

import com.codekaffe.valentine.block.ModBlocks;
import com.codekaffe.valentine.block.entity.ModBlockEntities;
import com.codekaffe.valentine.item.ModItemGroups;
import com.codekaffe.valentine.item.ModItems;
import com.codekaffe.valentine.recipe.ModRecipes;
import com.codekaffe.valentine.screen.ModScreenHandlers;
import com.codekaffe.valentine.sound.ModSounds;
import com.codekaffe.valentine.util.ModCustomTrades;
import com.codekaffe.valentine.util.ModLootTableModifiers;
import com.codekaffe.valentine.world.gen.ModWorldGeneration;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KafValentine implements ModInitializer {
    public static final String MOD_ID = "kafvalentine";
    public static final Logger LOGGER = LoggerFactory.getLogger("kafvalentine");

    public static Identifier id(String name) {
        return new Identifier(MOD_ID, name);
    }

    @Override
    public void onInitialize() {
        ModItemGroups.registerItemGroups();
        ModItems.registerModItems();
        ModBlocks.registerModBlocks();
        ModLootTableModifiers.modifyLootTables();
        ModCustomTrades.registerCustomTrades();
        ModSounds.registerSounds();
        ModBlockEntities.registerBlockEntities();
        ModScreenHandlers.registerScreenHandlers();
        ModRecipes.registerRecipes();
        ModWorldGeneration.generateModWorldGen();
    }
}