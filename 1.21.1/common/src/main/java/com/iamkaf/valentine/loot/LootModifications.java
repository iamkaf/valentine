package com.iamkaf.valentine.loot;

import com.iamkaf.valentine.Valentine;
import dev.architectury.event.events.common.LootEvent;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.List;

public class LootModifications {
    private static final ResourceKey<LootTable> GRASS = Blocks.SHORT_GRASS.getLootTable();
    private static final ResourceKey<LootTable> TALL_GRASS = Blocks.TALL_GRASS.getLootTable();

    static {
        LootEvent.MODIFY_LOOT_TABLE.register((key, context, builtin) -> {
            if (builtin && (GRASS.equals(key) || TALL_GRASS.equals(key))) {
                // drop our modded seeds from grass
                modifyGrassDrops(context);
                Valentine.LOGGER.info("Modifying loot table: {}", key);
            }
            if (builtin && isVillageHouse(key)) {
                modifyVillageChests(context);
                Valentine.LOGGER.info("Modifying loot table: {}", key);
            }
        });
    }


    private static void modifyGrassDrops(LootEvent.LootTableModificationContext context) {
        LootPool.Builder pool = LootPool.lootPool()
                // the loot table will roll once
                .setRolls(ConstantValue.exactly(1))
                // with a 0.2f chance, it will:
                .when(LootItemRandomChanceCondition.randomChance(0.02f))
                // drop cotton candy seeds
                .add(LootItem.lootTableItem(Valentine.Items.COTTON_CANDY_SEEDS.get()))
                // with a count in between 1 and 1
                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 1.0f)));
        context.addPool(pool);
    }

    private static void modifyVillageChests(LootEvent.LootTableModificationContext context) {
        List<Item> COOKIES = List.of(
                Valentine.Items.SPECIAL_CHOCOLATE_COOKIE.get(),
                Valentine.Items.CARAMEL_COOKIE.get(),
                Valentine.Items.MEDIC_COOKIE.get(),
                Valentine.Items.APPLE_COOKIE.get(),
                Valentine.Items.GOOD_VISION_COOKIE.get(),
                Valentine.Items.ARISTEA_COOKIE.get(),
                Valentine.Items.EXPLOSIVE_COOKIE.get(),
                Valentine.Items.EVIL_COOKIE.get(),
                Valentine.Items.FIRE_COOKIE.get(),
                Valentine.Items.GLOW_COOKIE.get(),
                Valentine.Items.GOLDEN_COOKIE.get(),
                Valentine.Items.NETHER_WART_COOKIE.get(),
                Valentine.Items.ROCKET_COOKIE.get(),
                Valentine.Items.SPOOKY_COOKIE.get(),
                Valentine.Items.PECULIAR_COOKIE.get(),
                Valentine.Items.PRISMATIC_COOKIE.get(),
                Valentine.Items.CHORUS_COOKIE.get(),
                Valentine.Items.BERRY_COOKIE.get()
                // omega cookies do not go here
        );
        for (var cookie : COOKIES) {
            LootPool.Builder pool = LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1))
                    .when(LootItemRandomChanceCondition.randomChance(0.15f))
                    .add(LootItem.lootTableItem(cookie))
                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 4.0f)));
            context.addPool(pool);
        }
    }

    private static boolean isVillageHouse(ResourceKey<LootTable> key) {
        return key.toString().contains("house");
    }

    public static void init() {

    }
}
