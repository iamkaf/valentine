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
        itemModelGenerator.register(ModItems.MEDIC_COOKIE, Models.GENERATED);
        itemModelGenerator.register(ModItems.SPECIAL_MEDIC_COOKIE, Models.GENERATED);
        itemModelGenerator.register(ModItems.GOOD_VISION_COOKIE, Models.GENERATED);
        itemModelGenerator.register(ModItems.SPECIAL_GOOD_VISION_COOKIE, Models.GENERATED);
        itemModelGenerator.register(ModItems.FIRE_COOKIE, Models.GENERATED);
        itemModelGenerator.register(ModItems.SPECIAL_FIRE_COOKIE, Models.GENERATED);
        itemModelGenerator.register(ModItems.APPLE_COOKIE, Models.GENERATED);
        itemModelGenerator.register(ModItems.SPECIAL_APPLE_COOKIE, Models.GENERATED);
        itemModelGenerator.register(ModItems.MELON_COOKIE, Models.GENERATED);
        itemModelGenerator.register(ModItems.SPECIAL_MELON_COOKIE, Models.GENERATED);
        itemModelGenerator.register(ModItems.NETHER_WART_COOKIE, Models.GENERATED);
        itemModelGenerator.register(ModItems.SPECIAL_NETHER_WART_COOKIE, Models.GENERATED);
        itemModelGenerator.register(ModItems.GLOW_COOKIE, Models.GENERATED);
        itemModelGenerator.register(ModItems.SPECIAL_GLOW_COOKIE, Models.GENERATED);
        itemModelGenerator.register(ModItems.CARAMEL_COOKIE, Models.GENERATED);
        itemModelGenerator.register(ModItems.SPECIAL_CARAMEL_COOKIE, Models.GENERATED);
        itemModelGenerator.register(ModItems.EXPLOSIVE_COOKIE, Models.GENERATED);
        itemModelGenerator.register(ModItems.SPECIAL_EXPLOSIVE_COOKIE, Models.GENERATED);
        itemModelGenerator.register(ModItems.GOLDEN_COOKIE, Models.GENERATED);
        itemModelGenerator.register(ModItems.SPECIAL_GOLDEN_COOKIE, Models.GENERATED);
        itemModelGenerator.register(ModItems.EVIL_COOKIE, Models.GENERATED);
        itemModelGenerator.register(ModItems.SPECIAL_EVIL_COOKIE, Models.GENERATED);
        itemModelGenerator.register(ModItems.ARISTEA_COOKIE, Models.GENERATED);
        itemModelGenerator.register(ModItems.SPECIAL_ARISTEA_COOKIE, Models.GENERATED);
        itemModelGenerator.register(ModItems.ROCKET_COOKIE, Models.GENERATED);
        itemModelGenerator.register(ModItems.SPECIAL_ROCKET_COOKIE, Models.GENERATED);
        itemModelGenerator.register(ModItems.COOKIE_BOOK, Models.GENERATED);

        itemModelGenerator.register(ModItems.COTTON_CANDY, Models.GENERATED);
    }
}
