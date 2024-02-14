package com.codekaffe.valentine.util;

import com.codekaffe.valentine.item.ModItems;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Identifier;

public class ModLootTableModifiers {
    private static final Identifier GRASS_ID = new Identifier("minecraft", "blocks/tall_grass");

    public static void modifyLootTables() {
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            if (GRASS_ID.equals(id)) {
                LootPool.Builder poolBuilder = LootPool
                        .builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.5f))
                        .with(ItemEntry.builder(ModItems.COTTON_CANDY_SEEDS))
                        .apply(SetCountLootFunction
                                .builder(UniformLootNumberProvider.create(1.0f, 1.0f))
                                .build());

                tableBuilder.pool(poolBuilder.build());
            }
        });
    }
}
