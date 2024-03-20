package com.codekaffe.valentine.util;

import com.codekaffe.valentine.item.ModItems;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.item.Item;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class ModLootTableModifiers {
    private static final Identifier GRASS_ID = new Identifier("minecraft", "blocks/grass");
    private static final Identifier TALL_GRASS_ID = new Identifier("minecraft",
            "blocks/tall_grass"
    );

    private static final List<Identifier> HOUSES_WITH_COOKIES = List.of(villageIdHelper("desert"),
            villageIdHelper("plains"),
            villageIdHelper("savanna"),
            villageIdHelper("snowy"),
            villageIdHelper("taiga")
    );
    private static final List<Item> COOKIES = List.of(ModItems.SPECIAL_CHOCOLATE_COOKIE,
            ModItems.CARAMEL_COOKIE,
            ModItems.MEDIC_COOKIE,
            ModItems.APPLE_COOKIE,
            ModItems.GOOD_VISION_COOKIE,
            ModItems.ARISTEA_COOKIE,
            ModItems.EXPLOSIVE_COOKIE,
            ModItems.EVIL_COOKIE,
            ModItems.FIRE_COOKIE,
            ModItems.GLOW_COOKIE,
            ModItems.GOLDEN_COOKIE,
            ModItems.NETHER_WART_COOKIE,
            ModItems.ROCKET_COOKIE,
            ModItems.SPOOKY_COOKIE,
            ModItems.PECULIAR_COOKIE,
            ModItems.PRISMATIC_COOKIE,
            ModItems.CHORUS_COOKIE
    );

    private static Identifier villageIdHelper(String villageType) {
        return new Identifier("minecraft", "chests/village/village_" + villageType + "_house");
    }

    public static void modifyLootTables() {
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            var pools = new ArrayList<LootPool>();
            if (GRASS_ID.equals(id) || TALL_GRASS_ID.equals(id)) {
                LootPool.Builder poolBuilder = LootPool
                        .builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.0625f))
                        .with(ItemEntry.builder(ModItems.COTTON_CANDY_SEEDS))
                        .apply(SetCountLootFunction
                                .builder(UniformLootNumberProvider.create(1.0f, 1.0f))
                                .build());

                pools.add(poolBuilder.build());
            }

            for (var chestId : HOUSES_WITH_COOKIES) {
                if (chestId.equals(id)) {
                    for (var cookie : COOKIES) {
                        LootPool.Builder poolBuilder = LootPool
                                .builder()
                                .rolls(ConstantLootNumberProvider.create(1))
                                .conditionally(RandomChanceLootCondition.builder(0.08f))
                                .with(ItemEntry.builder(cookie))
                                .apply(SetCountLootFunction
                                        .builder(UniformLootNumberProvider.create(1.0f, 4.0f))
                                        .build());
                        pools.add(poolBuilder.build());
                    }
                }
            }
            tableBuilder.pools(pools);
        });
    }
}
