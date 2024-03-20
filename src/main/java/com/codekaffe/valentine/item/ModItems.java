package com.codekaffe.valentine.item;

import com.codekaffe.valentine.KafValentine;
import com.codekaffe.valentine.block.ModBlocks;
import com.codekaffe.valentine.item.custom.*;
import io.wispforest.lavender.book.LavenderBookItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

public class ModItems {
    public static final Item SPECIAL_CHOCOLATE_COOKIE = registerItem(
            "special_chocolate_cookie",
            new SpecialChocolateCookie(new FabricItemSettings().food(ModFoodComponents.SPECIAL_CHOCOLATE_COOKIE))
    );
    public static final Item EXTRA_SPECIAL_CHOCOLATE_COOKIE = registerItem(
            "extra_special_chocolate_cookie",
            new ExtraSpecialChocolateCookie(new FabricItemSettings().food(ModFoodComponents.EXTRA_SPECIAL_CHOCOLATE_COOKIE))
    );
    public static final Item MEDIC_COOKIE = registerItem(
            "medic_cookie",
            new MedicCookie(new FabricItemSettings().food(MedicCookie.FOOD_COMPONENT).maxCount(16))
    );
    public static final Item SPECIAL_MEDIC_COOKIE = registerItem(
            "special_medic_cookie",
            new SpecialMedicCookie(new FabricItemSettings()
                    .food(MedicCookie.FOOD_COMPONENT)
                    .maxCount(16))
    );
    public static final Item GOOD_VISION_COOKIE = registerItem(
            "good_vision_cookie",
            new GoodVisionCookie(new FabricItemSettings().food(GoodVisionCookie.FOOD_COMPONENT))
    );
    public static final Item SPECIAL_GOOD_VISION_COOKIE = registerItem(
            "special_good_vision_cookie",
            new SpecialGoodVisionCookie(new FabricItemSettings().food(SpecialGoodVisionCookie.FOOD_COMPONENT))
    );
    public static final Item FIRE_COOKIE = registerItem(
            "fire_cookie",
            new FireCookie(new FabricItemSettings().food(FireCookie.FOOD_COMPONENT))
    );
    public static final Item SPECIAL_FIRE_COOKIE = registerItem(
            "special_fire_cookie",
            new SpecialFireCookie(new FabricItemSettings().food(SpecialFireCookie.FOOD_COMPONENT))
    );
    public static final Item MELON_COOKIE = registerItem(
            "melon_cookie",
            new MelonCookie(new FabricItemSettings().food(MelonCookie.FOOD_COMPONENT))
    );
    public static final Item SPECIAL_MELON_COOKIE = registerItem(
            "special_melon_cookie",
            new SpecialMelonCookie(new FabricItemSettings().food(SpecialMelonCookie.FOOD_COMPONENT))
    );
    public static final Item APPLE_COOKIE = registerItem(
            "apple_cookie",
            new AppleCookie(new FabricItemSettings().food(AppleCookie.FOOD_COMPONENT))
    );
    public static final Item SPECIAL_APPLE_COOKIE = registerItem(
            "special_apple_cookie",
            new SpecialAppleCookie(new FabricItemSettings().food(SpecialAppleCookie.FOOD_COMPONENT))
    );
    public static final Item NETHER_WART_COOKIE = registerItem(
            "nether_wart_cookie",
            new NetherWartCookie(new FabricItemSettings().food(NetherWartCookie.FOOD_COMPONENT))
    );
    public static final Item SPECIAL_NETHER_WART_COOKIE = registerItem(
            "special_nether_wart_cookie",
            new SpecialNetherWartCookie(new FabricItemSettings().food(SpecialNetherWartCookie.FOOD_COMPONENT))
    );
    public static final Item GLOW_COOKIE = registerItem(
            "glow_cookie",
            new GlowCookie(new FabricItemSettings().food(GlowCookie.FOOD_COMPONENT))
    );
    public static final Item SPECIAL_GLOW_COOKIE = registerItem(
            "special_glow_cookie",
            new SpecialGlowCookie(new FabricItemSettings().food(SpecialGlowCookie.FOOD_COMPONENT))
    );
    public static final Item CARAMEL_COOKIE = registerItem(
            "caramel_cookie",
            new CaramelCookie(new FabricItemSettings().food(CaramelCookie.FOOD_COMPONENT))
    );
    public static final Item SPECIAL_CARAMEL_COOKIE = registerItem(
            "special_caramel_cookie",
            new SpecialCaramelCookie(new FabricItemSettings().food(SpecialCaramelCookie.FOOD_COMPONENT))
    );
    public static final Item EXPLOSIVE_COOKIE = registerItem(
            "explosive_cookie",
            new ExplosiveCookie(new FabricItemSettings().food(ExplosiveCookie.FOOD_COMPONENT))
    );
    public static final Item SPECIAL_EXPLOSIVE_COOKIE = registerItem(
            "special_explosive_cookie",
            new SpecialExplosiveCookie(new FabricItemSettings().food(SpecialExplosiveCookie.FOOD_COMPONENT))
    );
    public static final Item GOLDEN_COOKIE = registerItem(
            "golden_cookie",
            new GoldenCookie(new FabricItemSettings()
                    .food(GoldenCookie.FOOD_COMPONENT)
                    .rarity(Rarity.RARE))
    );
    public static final Item SPECIAL_GOLDEN_COOKIE = registerItem(
            "special_golden_cookie",
            new SpecialGoldenCookie(new FabricItemSettings()
                    .food(SpecialGoldenCookie.FOOD_COMPONENT)
                    .rarity(Rarity.EPIC))
    );
    public static final Item EVIL_COOKIE = registerItem(
            "evil_cookie",
            new EvilCookie(new FabricItemSettings().food(EvilCookie.FOOD_COMPONENT))
    );
    public static final Item SPECIAL_EVIL_COOKIE = registerItem(
            "special_evil_cookie",
            new SpecialEvilCookie(new FabricItemSettings().food(SpecialEvilCookie.FOOD_COMPONENT))
    );

    public static final Item ARISTEA_COOKIE = registerItem(
            "aristea_cookie",
            new AristeaCookie(new FabricItemSettings().food(AristeaCookie.FOOD_COMPONENT))
    );
    public static final Item SPECIAL_ARISTEA_COOKIE = registerItem(
            "special_aristea_cookie",
            new SpecialAristeaCookie(new FabricItemSettings().food(SpecialAristeaCookie.FOOD_COMPONENT))
    );

    public static final Item ROCKET_COOKIE = registerItem(
            "rocket_cookie",
            new RocketCookie(new FabricItemSettings().food(RocketCookie.FOOD_COMPONENT))
    );
    public static final Item SPECIAL_ROCKET_COOKIE = registerItem(
            "special_rocket_cookie",
            new SpecialRocketCookie(new FabricItemSettings().food(SpecialRocketCookie.FOOD_COMPONENT))
    );
    public static final Item SPOOKY_COOKIE = registerItem(
            "spooky_cookie",
            new SpookyCookie(new FabricItemSettings().food(SpookyCookie.FOOD_COMPONENT))
    );
    public static final Item SPECIAL_SPOOKY_COOKIE = registerItem(
            "special_spooky_cookie",
            new SpecialSpookyCookie(new FabricItemSettings().food(SpecialSpookyCookie.FOOD_COMPONENT))
    );
    public static final Item PECULIAR_COOKIE = registerItem(
            "peculiar_cookie",
            new PeculiarCookie(new FabricItemSettings().food(PeculiarCookie.FOOD_COMPONENT))
    );
    public static final Item SPECIAL_PECULIAR_COOKIE = registerItem(
            "special_peculiar_cookie",
            new SpecialPeculiarCookie(new FabricItemSettings().food(SpecialPeculiarCookie.FOOD_COMPONENT))
    );
    public static final Item PRISMATIC_COOKIE = registerItem(
            "prismatic_cookie",
            new PrismaticCookie(new FabricItemSettings().food(PrismaticCookie.FOOD_COMPONENT))
    );
    public static final Item SPECIAL_PRISMATIC_COOKIE = registerItem(
            "special_prismatic_cookie",
            new SpecialPrismaticCookie(new FabricItemSettings().food(SpecialPrismaticCookie.FOOD_COMPONENT))
    );
    public static final Item CHORUS_COOKIE = registerItem(
            "chorus_cookie",
            new ChorusCookie(new FabricItemSettings().food(ChorusCookie.FOOD_COMPONENT))
    );
    public static final Item SPECIAL_CHORUS_COOKIE = registerItem(
            "special_chorus_cookie",
            new SpecialChorusCookie(new FabricItemSettings().food(SpecialChorusCookie.FOOD_COMPONENT))
    );

    public static final Item COTTON_CANDY = registerItem(
            "cotton_candy",
            new Item(new FabricItemSettings().food(ModFoodComponents.COTTON_CANDY))
    );
    public static final Item COTTON_CANDY_SEEDS = registerItem(
            "cotton_candy_seeds",
            new AliasedBlockItem(ModBlocks.COTTON_CANDY_CROP, new FabricItemSettings())
    );

    public static final Item COOKIE_BOOK = LavenderBookItem.registerForBook(
            KafValentine.id("cookies"),
            KafValentine.id("cookie_book"),
            new FabricItemSettings().maxCount(1)
    );

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(KafValentine.MOD_ID, name), item);
    }

    public static void registerModItems() {
        KafValentine.LOGGER.info("Registering mod items for " + KafValentine.MOD_ID);
    }
}
