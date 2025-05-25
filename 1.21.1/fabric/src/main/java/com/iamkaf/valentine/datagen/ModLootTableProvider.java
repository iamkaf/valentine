package com.iamkaf.valentine.datagen;


import com.iamkaf.valentine.Valentine;
import com.iamkaf.valentine.block.CottonCandyCropBlock;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;

import java.util.concurrent.CompletableFuture;

public class ModLootTableProvider extends FabricBlockLootTableProvider {

    public ModLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        dropSelf(Valentine.Blocks.LOVEY_DOVEY_INFUSER.get());

        LootItemCondition.Builder cropLootCondition =
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(Valentine.Blocks.COTTON_CANDY_CROP.get())
                        .setProperties(StatePropertiesPredicate.Builder.properties()
                                .hasProperty(CottonCandyCropBlock.AGE, 5));

        LootTable.Builder cottonCandyCropDrops = createCropDrops(
                Valentine.Blocks.COTTON_CANDY_CROP.get(),
                Valentine.Items.COTTON_CANDY.get(),
                Valentine.Items.COTTON_CANDY_SEEDS.get(),
                cropLootCondition
        );
        add(Valentine.Blocks.COTTON_CANDY_CROP.get(), cottonCandyCropDrops);

        dropSelf(Valentine.Blocks.COTTON_CANDY_BLOCK.get());

        dropSelf(Valentine.Blocks.ARISTEA.get());
        dropPottedContents(Valentine.Blocks.POTTED_ARISTEA.get());
    }
}
