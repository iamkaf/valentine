package com.iamkaf.valentine.datagen;



import com.iamkaf.valentine.Valentine;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_PICKAXE).add(Valentine.Blocks.LOVEY_DOVEY_INFUSER.get());
        getOrCreateTagBuilder(BlockTags.NEEDS_STONE_TOOL).add(Valentine.Blocks.LOVEY_DOVEY_INFUSER.get());
        getOrCreateTagBuilder(BlockTags.FLOWERS).add(Valentine.Blocks.ARISTEA.get());
        getOrCreateTagBuilder(BlockTags.SMALL_FLOWERS).add(Valentine.Blocks.ARISTEA.get());
        getOrCreateTagBuilder(BlockTags.BEE_GROWABLES).add(Valentine.Blocks.ARISTEA.get());
        getOrCreateTagBuilder(BlockTags.BEE_GROWABLES).add(Valentine.Blocks.COTTON_CANDY_CROP.get());
        getOrCreateTagBuilder(BlockTags.MAINTAINS_FARMLAND).add(Valentine.Blocks.COTTON_CANDY_CROP.get());
        getOrCreateTagBuilder(BlockTags.CROPS).add(Valentine.Blocks.COTTON_CANDY_CROP.get());
    }
}
