package com.codekaffe.valentine.datagen;

import com.codekaffe.valentine.block.ModBlocks;
import com.codekaffe.valentine.item.ModItems;
import com.codekaffe.valentine.util.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryWrapper;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public ModItemTagProvider(
            FabricDataOutput output,
            CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture
    ) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        // Tags for all cookies
        List<Item> cookies = List.of(
                ModItems.SPECIAL_CHOCOLATE_COOKIE,
                ModItems.EXTRA_SPECIAL_CHOCOLATE_COOKIE,
                ModItems.MEDIC_COOKIE,
                ModItems.SPECIAL_MEDIC_COOKIE,
                ModItems.GOOD_VISION_COOKIE,
                ModItems.SPECIAL_GOOD_VISION_COOKIE,
                ModItems.FIRE_COOKIE,
                ModItems.SPECIAL_FIRE_COOKIE,
                ModItems.MELON_COOKIE,
                ModItems.SPECIAL_MELON_COOKIE,
                ModItems.APPLE_COOKIE,
                ModItems.SPECIAL_APPLE_COOKIE,
                ModItems.NETHER_WART_COOKIE,
                ModItems.SPECIAL_NETHER_WART_COOKIE,
                ModItems.GLOW_COOKIE,
                ModItems.SPECIAL_GLOW_COOKIE,
                ModItems.CARAMEL_COOKIE,
                ModItems.SPECIAL_CARAMEL_COOKIE,
                ModItems.EXPLOSIVE_COOKIE,
                ModItems.SPECIAL_EXPLOSIVE_COOKIE,
                ModItems.GOLDEN_COOKIE,
                ModItems.SPECIAL_GOLDEN_COOKIE,
                ModItems.EVIL_COOKIE,
                ModItems.SPECIAL_EVIL_COOKIE,
                ModItems.ARISTEA_COOKIE,
                ModItems.SPECIAL_ARISTEA_COOKIE,
                ModItems.ROCKET_COOKIE,
                ModItems.SPECIAL_ROCKET_COOKIE
        );

        var cookieBuilder = getOrCreateTagBuilder(ModTags.Items.COOKIES);
        var modCookieBuilder = getOrCreateTagBuilder(ModTags.Items.MOD_COOKIES);
        var originsBuilder = getOrCreateTagBuilder(ModTags.Items.UNDIET_FOODS);

        for (var cookie : cookies) {
            cookieBuilder.add(cookie);
            modCookieBuilder.add(cookie);
            originsBuilder.add(cookie);
        }

        // Additional cookie tags
        getOrCreateTagBuilder(ModTags.Items.GOLDEN_APPLES)
                .add(ModItems.GOLDEN_COOKIE)
                .add(ModItems.SPECIAL_GOLDEN_COOKIE);

        // Flower tags
        getOrCreateTagBuilder(ModTags.Items.ARISTEA).add(ModBlocks.ARISTEA.asItem());
        getOrCreateTagBuilder(ModTags.Items.FLOWERS).add(ModBlocks.ARISTEA.asItem());

        // Seed tags
        getOrCreateTagBuilder(ModTags.Items.SEEDS).add(ModItems.COTTON_CANDY_SEEDS);
        getOrCreateTagBuilder(ModTags.Items.MINECRAFT_SEEDS).add(ModItems.COTTON_CANDY_SEEDS);
        getOrCreateTagBuilder(ModTags.Items.BOTANIA_SEEDS).add(ModItems.COTTON_CANDY_SEEDS);
        getOrCreateTagBuilder(ModTags.Items.NATURALIST_SEEDS).add(ModItems.COTTON_CANDY_SEEDS);
    }
}
