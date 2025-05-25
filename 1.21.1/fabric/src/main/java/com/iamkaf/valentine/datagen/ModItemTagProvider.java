package com.iamkaf.valentine.datagen;


import com.iamkaf.valentine.Valentine;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        // Tags for all cookies
        List<Item> cookies = List.of(
                Valentine.Items.SPECIAL_CHOCOLATE_COOKIE.get(), Valentine.Items.EXTRA_SPECIAL_CHOCOLATE_COOKIE.get(), Valentine.Items.MEDIC_COOKIE.get(),
                Valentine.Items.SPECIAL_MEDIC_COOKIE.get(),
                Valentine.Items.GOOD_VISION_COOKIE.get(),
                Valentine.Items.SPECIAL_GOOD_VISION_COOKIE.get(),
                Valentine.Items.FIRE_COOKIE.get(),
                Valentine.Items.SPECIAL_FIRE_COOKIE.get(),
                Valentine.Items.MELON_COOKIE.get(),
                Valentine.Items.SPECIAL_MELON_COOKIE.get(),
                Valentine.Items.APPLE_COOKIE.get(),
                Valentine.Items.SPECIAL_APPLE_COOKIE.get(),
                Valentine.Items.NETHER_WART_COOKIE.get(),
                Valentine.Items.SPECIAL_NETHER_WART_COOKIE.get(),
                Valentine.Items.GLOW_COOKIE.get(),
                Valentine.Items.SPECIAL_GLOW_COOKIE.get(),
                Valentine.Items.CARAMEL_COOKIE.get(),
                Valentine.Items.SPECIAL_CARAMEL_COOKIE.get(),
                Valentine.Items.EXPLOSIVE_COOKIE.get(),
                Valentine.Items.SPECIAL_EXPLOSIVE_COOKIE.get(),
                Valentine.Items.GOLDEN_COOKIE.get(),
                Valentine.Items.SPECIAL_GOLDEN_COOKIE.get(),
                Valentine.Items.EVIL_COOKIE.get(),
                Valentine.Items.SPECIAL_EVIL_COOKIE.get(),
                Valentine.Items.ARISTEA_COOKIE.get(),
                Valentine.Items.SPECIAL_ARISTEA_COOKIE.get(),
                Valentine.Items.ROCKET_COOKIE.get(),
                Valentine.Items.SPECIAL_ROCKET_COOKIE.get(),
                Valentine.Items.SPOOKY_COOKIE.get(),
                Valentine.Items.SPECIAL_SPOOKY_COOKIE.get(),
                Valentine.Items.PECULIAR_COOKIE.get(),
                Valentine.Items.SPECIAL_PECULIAR_COOKIE.get(),
                Valentine.Items.PRISMATIC_COOKIE.get(),
                Valentine.Items.SPECIAL_PRISMATIC_COOKIE.get(),
                Valentine.Items.CHORUS_COOKIE.get(),
                Valentine.Items.SPECIAL_CHORUS_COOKIE.get(),
                Valentine.Items.BERRY_COOKIE.get(),
                Valentine.Items.SPECIAL_BERRY_COOKIE.get(),
                Valentine.Items.CHORUS_COOKIE.get(),
                Valentine.Items.SPECIAL_CHORUS_COOKIE.get(),
                Valentine.Items.OMEGA_COOKIE.get(),
                Valentine.Items.SPECIAL_OMEGA_COOKIE.get()
        );

        var cookieBuilder = getOrCreateTagBuilder(Valentine.Tags.Items.COOKIES);
        var modCookieBuilder = getOrCreateTagBuilder(Valentine.Tags.Items.MOD_COOKIES);
        var originsBuilder = getOrCreateTagBuilder(Valentine.Tags.Items.UNDIET_FOODS);

        for (var cookie : cookies) {
            cookieBuilder.add(cookie);
            modCookieBuilder.add(cookie);
            originsBuilder.add(cookie);
        }

        // Additional cookie tags
        getOrCreateTagBuilder(Valentine.Tags.Items.GOLDEN_APPLES)
                .add(Valentine.Items.GOLDEN_COOKIE.get())
                .add(Valentine.Items.SPECIAL_GOLDEN_COOKIE.get());

        // Flower tags
        getOrCreateTagBuilder(Valentine.Tags.Items.ARISTEA).add(Valentine.Blocks.ARISTEA.get().asItem());
        getOrCreateTagBuilder(Valentine.Tags.Items.FLOWERS).add(Valentine.Blocks.ARISTEA.get().asItem());
        getOrCreateTagBuilder(ItemTags.SMALL_FLOWERS).add(Valentine.Blocks.ARISTEA.get().asItem());

        // Seed tags
        getOrCreateTagBuilder(Valentine.Tags.Items.SEEDS).add(Valentine.Items.COTTON_CANDY_SEEDS.get());
        getOrCreateTagBuilder(Valentine.Tags.Items.MINECRAFT_SEEDS).add(Valentine.Items.COTTON_CANDY_SEEDS.get());
        getOrCreateTagBuilder(ItemTags.CHICKEN_FOOD).add(Valentine.Items.COTTON_CANDY_SEEDS.get());
        getOrCreateTagBuilder(Valentine.Tags.Items.BOTANIA_SEEDS).add(Valentine.Items.COTTON_CANDY_SEEDS.get());
        getOrCreateTagBuilder(Valentine.Tags.Items.NATURALIST_SEEDS).add(Valentine.Items.COTTON_CANDY_SEEDS.get());
    }
}
