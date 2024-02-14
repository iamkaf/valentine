package com.codekaffe.valentine.item;

import com.codekaffe.valentine.KafValentine;
import com.codekaffe.valentine.block.ModBlocks;
import com.codekaffe.valentine.item.custom.ExtraSpecialChocolateCookie;
import com.codekaffe.valentine.item.custom.SpecialChocolateCookie;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item SPECIAL_CHOCOLATE_COOKIE = registerItem(
            "special_chocolate_cookie",
            new SpecialChocolateCookie(new FabricItemSettings().food(ModFoodComponents.SPECIAL_CHOCOLATE_COOKIE))
    );
    public static final Item EXTRA_SPECIAL_CHOCOLATE_COOKIE = registerItem(
            "extra_special_chocolate_cookie",
            new ExtraSpecialChocolateCookie(new FabricItemSettings().food(ModFoodComponents.EXTRA_SPECIAL_CHOCOLATE_COOKIE))
    );

    public static final Item COTTON_CANDY = registerItem(
            "cotton_candy",
            new Item(new FabricItemSettings().food(ModFoodComponents.COTTON_CANDY))
    );

    public static final Item COTTON_CANDY_SEEDS = registerItem(
            "cotton_candy_seeds",
            new AliasedBlockItem(ModBlocks.COTTON_CANDY_CROP, new FabricItemSettings())
    );

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(KafValentine.MOD_ID, name), item);
    }

    public static void registerModItems() {
        KafValentine.LOGGER.info("Registering mod items for " + KafValentine.MOD_ID);
    }
}
