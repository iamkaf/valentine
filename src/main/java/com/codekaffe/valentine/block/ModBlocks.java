package com.codekaffe.valentine.block;

import com.codekaffe.valentine.KafValentine;
import com.codekaffe.valentine.block.custom.CottonCandyCropBlock;
import com.codekaffe.valentine.block.custom.LoveyDoveyInfuserBlock;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowerBlock;
import net.minecraft.block.FlowerPotBlock;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {

    public static final Block LOVEY_DOVEY_INFUSER = registerBlock("lovey_dovey_infuser",
            new LoveyDoveyInfuserBlock(FabricBlockSettings.copyOf(Blocks.COPPER_BLOCK).nonOpaque())
    );

    public static final Block COTTON_CANDY_CROP = Registry.register(Registries.BLOCK,
            new Identifier(KafValentine.MOD_ID, "cotton_candy_crop"),
            new CottonCandyCropBlock(FabricBlockSettings.copyOf(Blocks.WHEAT))
    );

    public static final Block ARISTEA = registerBlock("aristea",
            new FlowerBlock(StatusEffects.SPEED,
                    10,
                    FabricBlockSettings.copyOf(Blocks.ALLIUM).nonOpaque().noCollision()
            )
    );
    public static final Block POTTED_ARISTEA = Registry.register(Registries.BLOCK,
            new Identifier(KafValentine.MOD_ID, "potted_aristea"),
            new FlowerPotBlock(ARISTEA,
                    FabricBlockSettings.copyOf(Blocks.POTTED_ALLIUM).nonOpaque()
            )
    );

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK,
                new Identifier(KafValentine.MOD_ID, name),
                block
        );
    }

    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM,
                new Identifier(KafValentine.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings())
        );
    }

    public static void registerModBlocks() {
        KafValentine.LOGGER.info("Registering Mod Blocks for " + KafValentine.MOD_ID);
    }
}
