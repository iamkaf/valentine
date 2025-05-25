package com.iamkaf.valentine.datagen;

import com.iamkaf.valentine.Valentine;
import com.iamkaf.valentine.block.CottonCandyCropBlock;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TexturedModel;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {
        blockStateModelGenerator.createCropBlock(
                Valentine.Blocks.COTTON_CANDY_CROP.get(),
                CottonCandyCropBlock.AGE,
                0,
                1,
                2,
                3,
                4,
                5,
                5,
                5
        );

        blockStateModelGenerator.createPlant(Valentine.Blocks.ARISTEA.get(), Valentine.Blocks.POTTED_ARISTEA.get(), BlockModelGenerators.PlantType.NOT_TINTED);

        blockStateModelGenerator.createTrivialCube(Valentine.Blocks.COTTON_CANDY_BLOCK.get());
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator) {
        itemModelGenerator.generateFlatItem(Valentine.Items.SPECIAL_CHOCOLATE_COOKIE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(Valentine.Items.EXTRA_SPECIAL_CHOCOLATE_COOKIE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(Valentine.Items.MEDIC_COOKIE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(Valentine.Items.SPECIAL_MEDIC_COOKIE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(Valentine.Items.GOOD_VISION_COOKIE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(Valentine.Items.SPECIAL_GOOD_VISION_COOKIE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(Valentine.Items.FIRE_COOKIE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(Valentine.Items.SPECIAL_FIRE_COOKIE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(Valentine.Items.APPLE_COOKIE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(Valentine.Items.SPECIAL_APPLE_COOKIE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(Valentine.Items.MELON_COOKIE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(Valentine.Items.SPECIAL_MELON_COOKIE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(Valentine.Items.NETHER_WART_COOKIE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(Valentine.Items.SPECIAL_NETHER_WART_COOKIE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(Valentine.Items.GLOW_COOKIE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(Valentine.Items.SPECIAL_GLOW_COOKIE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(Valentine.Items.CARAMEL_COOKIE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(Valentine.Items.SPECIAL_CARAMEL_COOKIE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(Valentine.Items.EXPLOSIVE_COOKIE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(Valentine.Items.SPECIAL_EXPLOSIVE_COOKIE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(Valentine.Items.GOLDEN_COOKIE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(Valentine.Items.SPECIAL_GOLDEN_COOKIE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(Valentine.Items.EVIL_COOKIE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(Valentine.Items.SPECIAL_EVIL_COOKIE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(Valentine.Items.ARISTEA_COOKIE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(Valentine.Items.SPECIAL_ARISTEA_COOKIE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(Valentine.Items.ROCKET_COOKIE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(Valentine.Items.SPECIAL_ROCKET_COOKIE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(Valentine.Items.SPOOKY_COOKIE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(Valentine.Items.SPECIAL_SPOOKY_COOKIE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(Valentine.Items.PECULIAR_COOKIE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(Valentine.Items.SPECIAL_PECULIAR_COOKIE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(Valentine.Items.PRISMATIC_COOKIE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(Valentine.Items.SPECIAL_PRISMATIC_COOKIE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(Valentine.Items.CHORUS_COOKIE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(Valentine.Items.SPECIAL_CHORUS_COOKIE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(Valentine.Items.BERRY_COOKIE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(Valentine.Items.SPECIAL_BERRY_COOKIE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(Valentine.Items.OMEGA_COOKIE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(Valentine.Items.SPECIAL_OMEGA_COOKIE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(Valentine.Items.COTTON_CANDY.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(Valentine.Items.CANDIED_COTTON_CANDY_CANDY.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(Valentine.Items.LOVE.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(Valentine.Blocks.ARISTEA.get().asItem(), ModelTemplates.FLAT_ITEM);
    }
}
