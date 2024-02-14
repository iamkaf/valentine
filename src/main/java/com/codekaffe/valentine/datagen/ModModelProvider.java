package com.codekaffe.valentine.datagen;

import com.codekaffe.valentine.block.ModBlocks;
import com.codekaffe.valentine.block.custom.CottonCandyCropBlock;
import com.codekaffe.valentine.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerCrop(ModBlocks.COTTON_CANDY_CROP,
                CottonCandyCropBlock.AGE,
                0,
                1,
                2,
                3,
                4,
                5
        );

        blockStateModelGenerator.registerFlowerPotPlant(ModBlocks.ARISTEA,
                ModBlocks.POTTED_ARISTEA,
                BlockStateModelGenerator.TintType.NOT_TINTED
        );

//        blockStateModelGenerator.registerSimpleState(ModBlocks.LOVEY_DOVEY_INFUSER);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.LOVEY_DOVEY_INFUSER);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.SPECIAL_CHOCOLATE_COOKIE, Models.GENERATED);
        itemModelGenerator.register(ModItems.EXTRA_SPECIAL_CHOCOLATE_COOKIE, Models.GENERATED);

        itemModelGenerator.register(ModItems.COTTON_CANDY, Models.GENERATED);
    }
}
