package com.iamkaf.valentine.loot;

import com.iamkaf.amber.api.event.v1.events.common.LootEvents;
import com.iamkaf.valentine.Valentine;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
//? if >=26.3 {
/*import net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProviders;
*///?} else {
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
//?}

import java.util.List;

public class LootModifications {
    private static final ResourceKey<LootTable> GRASS = Blocks.SHORT_GRASS.getLootTable().orElseThrow();
    private static final ResourceKey<LootTable> TALL_GRASS = Blocks.TALL_GRASS.getLootTable().orElseThrow();

    static {
        LootEvents.MODIFY.register((key, addPool) -> {
            Identifier grass = GRASS.identifier();
            Identifier tallGrass = TALL_GRASS.identifier();
            if (grass.equals(key) || tallGrass.equals(key)) {
                modifyGrassDrops(addPool);
            }
            if (isVillageHouse(key)) {
                modifyVillageChests(addPool);
            }
        });
    }


    private static void modifyGrassDrops(java.util.function.Consumer<LootPool.Builder> addPool) {
        LootPool.Builder pool = LootPool.lootPool()
                //? if >=26.3
                /*.setRolls(ContextIntProviders.exactly(1))*/
                //? if <26.3
                .setRolls(ConstantValue.exactly(1))
                .when(LootItemRandomChanceCondition.randomChance(0.02f))
                .add(LootItem.lootTableItem(Valentine.Items.COTTON_CANDY_SEEDS.get()))
                //? if >=26.3
                /*.apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 1)));*/
                //? if <26.3
                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 1.0f)));
        addPool.accept(pool);
    }

    private static void modifyVillageChests(java.util.function.Consumer<LootPool.Builder> addPool) {
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
                    //? if >=26.3
                    /*.setRolls(ContextIntProviders.exactly(1))*/
                    //? if <26.3
                    .setRolls(ConstantValue.exactly(1))
                    .when(LootItemRandomChanceCondition.randomChance(0.15f))
                    .add(LootItem.lootTableItem(cookie))
                    //? if >=26.3
                    /*.apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 4)));*/
                    //? if <26.3
                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 4.0f)));
            addPool.accept(pool);
        }
    }

    private static boolean isVillageHouse(Identifier key) {
        return key.toString().contains("house");
    }

    public static void init() {

    }
}
