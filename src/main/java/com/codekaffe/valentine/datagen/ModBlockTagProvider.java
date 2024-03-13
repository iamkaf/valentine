package com.codekaffe.valentine.datagen;

import com.codekaffe.valentine.block.ModBlocks;
import com.codekaffe.valentine.util.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(ModBlocks.LOVEY_DOVEY_INFUSER);
        getOrCreateTagBuilder(BlockTags.NEEDS_STONE_TOOL).add(ModBlocks.LOVEY_DOVEY_INFUSER);
        getOrCreateTagBuilder(ModTags.Blocks.FLOWERS).add(ModBlocks.ARISTEA);
        getOrCreateTagBuilder(BlockTags.FLOWERS).add(ModBlocks.ARISTEA);
        getOrCreateTagBuilder(BlockTags.SMALL_FLOWERS).add(ModBlocks.ARISTEA);
        getOrCreateTagBuilder(BlockTags.BEE_GROWABLES).add(ModBlocks.ARISTEA);
        getOrCreateTagBuilder(ModTags.Blocks.PLANTS).add(ModBlocks.COTTON_CANDY_CROP);
        getOrCreateTagBuilder(BlockTags.BEE_GROWABLES).add(ModBlocks.COTTON_CANDY_CROP);
        getOrCreateTagBuilder(BlockTags.MAINTAINS_FARMLAND).add(ModBlocks.COTTON_CANDY_CROP);
        getOrCreateTagBuilder(BlockTags.CROPS).add(ModBlocks.COTTON_CANDY_CROP);
    }
}
