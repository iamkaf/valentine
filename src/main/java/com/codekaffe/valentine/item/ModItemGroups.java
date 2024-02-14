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
                    .icon(() -> new ItemStack(ModItems.SPECIAL_CHOCOLATE_COOKIE))
                    .entries(((displayContext, entries) -> {
                        entries.add(ModItems.SPECIAL_CHOCOLATE_COOKIE);
                        entries.add(ModItems.EXTRA_SPECIAL_CHOCOLATE_COOKIE);
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
