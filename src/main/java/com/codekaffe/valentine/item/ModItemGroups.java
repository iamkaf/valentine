package com.codekaffe.valentine.item;

import com.codekaffe.valentine.KafValentine;
import com.codekaffe.valentine.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup VALENTINE_GROUP = Registry.register(
            Registries.ITEM_GROUP,
            new Identifier(KafValentine.MOD_ID, "valentine"),
            FabricItemGroup
                    .builder()
                    .displayName(Text.translatable("itemgroup.valentine"))
                    .icon(() -> new ItemStack(ModItems.APPLE_COOKIE))
                    .entries(((displayContext, entries) -> {
                        entries.add(ModItems.COOKIE_BOOK);
                        entries.add(ModItems.SPECIAL_CHOCOLATE_COOKIE);
                        entries.add(ModItems.EXTRA_SPECIAL_CHOCOLATE_COOKIE);
                        entries.add(ModItems.MEDIC_COOKIE);
                        entries.add(ModItems.SPECIAL_MEDIC_COOKIE);
                        entries.add(ModItems.GOOD_VISION_COOKIE);
                        entries.add(ModItems.SPECIAL_GOOD_VISION_COOKIE);
                        entries.add(ModItems.FIRE_COOKIE);
                        entries.add(ModItems.SPECIAL_FIRE_COOKIE);
                        entries.add(ModItems.MELON_COOKIE);
                        entries.add(ModItems.SPECIAL_MELON_COOKIE);
                        entries.add(ModItems.APPLE_COOKIE);
                        entries.add(ModItems.SPECIAL_APPLE_COOKIE);
                        entries.add(ModItems.NETHER_WART_COOKIE);
                        entries.add(ModItems.SPECIAL_NETHER_WART_COOKIE);
                        entries.add(ModItems.GLOW_COOKIE);
                        entries.add(ModItems.SPECIAL_GLOW_COOKIE);
                        entries.add(ModItems.CARAMEL_COOKIE);
                        entries.add(ModItems.SPECIAL_CARAMEL_COOKIE);
                        entries.add(ModItems.EXPLOSIVE_COOKIE);
                        entries.add(ModItems.SPECIAL_EXPLOSIVE_COOKIE);
                        entries.add(ModItems.GOLDEN_COOKIE);
                        entries.add(ModItems.SPECIAL_GOLDEN_COOKIE);
                        entries.add(ModItems.EVIL_COOKIE);
                        entries.add(ModItems.SPECIAL_EVIL_COOKIE);
                        entries.add(ModItems.ARISTEA_COOKIE);
                        entries.add(ModItems.SPECIAL_ARISTEA_COOKIE);
                        entries.add(ModItems.ROCKET_COOKIE);
                        entries.add(ModItems.SPECIAL_ROCKET_COOKIE);
                        entries.add(ModItems.SPOOKY_COOKIE);
                        entries.add(ModItems.SPECIAL_SPOOKY_COOKIE);
                        entries.add(ModItems.COTTON_CANDY);
                        entries.add(ModItems.COTTON_CANDY_SEEDS);
                        entries.add(ModBlocks.ARISTEA);
                        entries.add(ModBlocks.LOVEY_DOVEY_INFUSER);
                    }))
                    .build()
    );

    public static void registerItemGroups() {
        KafValentine.LOGGER.info("Registering Item Groups for " + KafValentine.MOD_ID);
    }
}
