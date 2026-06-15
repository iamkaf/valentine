package com.iamkaf.valentine;

import com.iamkaf.amber.api.core.v2.AmberInitializer;
import com.iamkaf.amber.api.event.v1.events.common.WorldEvents;
import com.iamkaf.amber.api.registry.v1.RegistrySupplier;
import com.iamkaf.valentine.block.AristeaBlock;
import com.iamkaf.valentine.block.CottonCandyCropBlock;
import com.iamkaf.valentine.block.LoveyDoveyInfuserBlock;
import com.iamkaf.valentine.effect.SmittenEffect;
import com.iamkaf.valentine.events.OnPat;
import com.iamkaf.valentine.item.CookieBuilder;
import com.iamkaf.valentine.item.ItemColorDataComponent;
import com.iamkaf.valentine.item.custom.CookieItem;
import com.iamkaf.valentine.item.custom.CottonCandyItem;
import com.iamkaf.valentine.item.custom.Love;
import com.iamkaf.valentine.loot.LootModifications;
import com.mojang.logging.LogUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public final class Valentine {
    public static final String MOD_ID = "kafvalentine";
    public static final Logger LOGGER = LogUtils.getLogger();

    public static void init() {
        LOGGER.info("For Aristea.");
        AmberInitializer.initialize(MOD_ID);

        Items.init();
        Blocks.init();
        DataComponents.init();
        Effects.init();
        Potions.init();
        Register.init();
        Upgrades.init();
        CreativeModeTabs.init();
        LootModifications.init();
        Compostables.init();
        OnPat.init();
    }

    /**
     * Creates resource location in the mod namespace with the given path.
     */
    public static Identifier resource(String path) {
        return Identifier.fromNamespaceAndPath(MOD_ID, path);
    }

    /**
     * Creates a resource key in the mod namespace with the given path.
     */
    public static ResourceKey<Item> itemKey(String path) {
        return ResourceKey.create(Registries.ITEM, resource(path));
    }

    public static ResourceKey<Block> blockKey(String path) {
        return ResourceKey.create(Registries.BLOCK, resource(path));
    }

    public static class CreativeModeTabs {
        public static final RegistrySupplier<CreativeModeTab> CREATIVE_MODE_TAB =
                Register.creativeModeTab(Items.APPLE_COOKIE, "valentine");

        public static List<Supplier<? extends ItemLike>> getCreativeModeTabItems() {
            List<Supplier<? extends ItemLike>> tabItems = new ArrayList<>();

            tabItems.add(Items.SPECIAL_CHOCOLATE_COOKIE);
            tabItems.add(Items.EXTRA_SPECIAL_CHOCOLATE_COOKIE);
            tabItems.add(Items.MEDIC_COOKIE);
            tabItems.add(Items.SPECIAL_MEDIC_COOKIE);
            tabItems.add(Items.GOOD_VISION_COOKIE);
            tabItems.add(Items.SPECIAL_GOOD_VISION_COOKIE);
            tabItems.add(Items.FIRE_COOKIE);
            tabItems.add(Items.SPECIAL_FIRE_COOKIE);
            tabItems.add(Items.MELON_COOKIE);
            tabItems.add(Items.SPECIAL_MELON_COOKIE);
            tabItems.add(Items.APPLE_COOKIE);
            tabItems.add(Items.SPECIAL_APPLE_COOKIE);
            tabItems.add(Items.NETHER_WART_COOKIE);
            tabItems.add(Items.SPECIAL_NETHER_WART_COOKIE);
            tabItems.add(Items.GLOW_COOKIE);
            tabItems.add(Items.SPECIAL_GLOW_COOKIE);
            tabItems.add(Items.CARAMEL_COOKIE);
            tabItems.add(Items.SPECIAL_CARAMEL_COOKIE);
            tabItems.add(Items.EXPLOSIVE_COOKIE);
            tabItems.add(Items.SPECIAL_EXPLOSIVE_COOKIE);
            tabItems.add(Items.GOLDEN_COOKIE);
            tabItems.add(Items.SPECIAL_GOLDEN_COOKIE);
            tabItems.add(Items.EVIL_COOKIE);
            tabItems.add(Items.SPECIAL_EVIL_COOKIE);
            tabItems.add(Items.ARISTEA_COOKIE);
            tabItems.add(Items.SPECIAL_ARISTEA_COOKIE);
            tabItems.add(Items.ROCKET_COOKIE);
            tabItems.add(Items.SPECIAL_ROCKET_COOKIE);
            tabItems.add(Items.SPOOKY_COOKIE);
            tabItems.add(Items.SPECIAL_SPOOKY_COOKIE);
            tabItems.add(Items.PECULIAR_COOKIE);
            tabItems.add(Items.SPECIAL_PECULIAR_COOKIE);
            tabItems.add(Items.PRISMATIC_COOKIE);
            tabItems.add(Items.SPECIAL_PRISMATIC_COOKIE);
            tabItems.add(Items.CHORUS_COOKIE);
            tabItems.add(Items.SPECIAL_CHORUS_COOKIE);
            tabItems.add(Items.BERRY_COOKIE);
            tabItems.add(Items.SPECIAL_BERRY_COOKIE);
            tabItems.add(Items.OMEGA_COOKIE);
            tabItems.add(Items.SPECIAL_OMEGA_COOKIE);
            tabItems.add(Items.COTTON_CANDY);
            tabItems.add(Items.CANDIED_COTTON_CANDY_CANDY);
            tabItems.add(Items.COTTON_CANDY_SEEDS);

            tabItems.add(Blocks.ARISTEA);
            tabItems.add(Blocks.LOVEY_DOVEY_INFUSER);
            tabItems.add(Blocks.COTTON_CANDY_BLOCK);

            tabItems.add(Items.LOVE);

            return tabItems;
        }

        static void init() {
            Valentine.CreativeModeTabs.getCreativeModeTabItems()
                    .forEach(item -> com.iamkaf.amber.api.registry.v1.creativetabs.CreativeModeTabRegistry
                            .getTabBuilder(CREATIVE_MODE_TAB.getId())
                            .addItem(() -> item.get()));
        }
    }

    public static class Items {
        public static final int NORMAL_COOKIE_NUTRITION = 1;
        public static final int SPECIAL_COOKIE_NUTRITION = 4;
        public static final float NORMAL_COOKIE_SATURATION = 0.1f;
        public static final float SPECIAL_COOKIE_SATURATION = 0.2f;

        public static final Supplier<Item> COTTON_CANDY = Register.item(
                "cotton_candy",
                () -> new CottonCandyItem(new Item.Properties().food(new FoodProperties.Builder().nutrition(4)
                        .saturationModifier(0.3f)
                        .build()).setId(itemKey("cotton_candy")))
        );

        public static final Supplier<Item> CANDIED_COTTON_CANDY_CANDY = Register.item(
                "candied_cotton_candy_candy",
                () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(6)
                        .saturationModifier(0.7f)
                        .build()).setId(itemKey("candied_cotton_candy_candy")))
        );

        public static final Supplier<Item> COTTON_CANDY_SEEDS = Register.item(
                "cotton_candy_seeds",
                () -> new BlockItem(Blocks.COTTON_CANDY_CROP.get(), new Item.Properties().setId(itemKey("cotton_candy_seeds")).useItemDescriptionPrefix())
        );

        public static final Supplier<CookieItem> SPECIAL_CHOCOLATE_COOKIE = Register.item(
                "special_chocolate_cookie",
                () -> new CookieBuilder("special_chocolate_cookie").setNutrition(NORMAL_COOKIE_NUTRITION)
                        .setSaturationModifier(NORMAL_COOKIE_SATURATION)
                        .build()
        );

        public static final Supplier<CookieItem> EXTRA_SPECIAL_CHOCOLATE_COOKIE = Register.item(
                "extra_special_chocolate_cookie",
                () -> new CookieBuilder("extra_special_chocolate_cookie").setNutrition(SPECIAL_COOKIE_NUTRITION)
                        .setSaturationModifier(SPECIAL_COOKIE_SATURATION)
                        .build()
        );

        public static final Supplier<CookieItem> MEDIC_COOKIE = Register.item(
                "medic_cookie",
                () -> new CookieBuilder("medic_cookie").setNutrition(NORMAL_COOKIE_NUTRITION)
                        .setSaturationModifier(NORMAL_COOKIE_SATURATION)
                        .addEffect(MobEffects.INSTANT_HEALTH, 1, 0, 1)
                        .setTooltipColor(ChatFormatting.RED)
                        .build()
        );

        public static final Supplier<CookieItem> SPECIAL_MEDIC_COOKIE = Register.item(
                "special_medic_cookie",
                () -> new CookieBuilder("special_medic_cookie").setNutrition(SPECIAL_COOKIE_NUTRITION)
                        .setSaturationModifier(SPECIAL_COOKIE_SATURATION)
                        .addEffect(MobEffects.INSTANT_HEALTH, 1, 0, 1)
                        .setTooltipColor(ChatFormatting.RED)
                        .build()
        );

        public static final Supplier<CookieItem> GOOD_VISION_COOKIE = Register.item(
                "good_vision_cookie",
                () -> new CookieBuilder("good_vision_cookie").setNutrition(NORMAL_COOKIE_NUTRITION)
                        .setSaturationModifier(NORMAL_COOKIE_SATURATION)
                        .addEffect(MobEffects.NIGHT_VISION, 2400, 0, 1)
                        .setTooltipColor(ChatFormatting.GOLD)
                        .build()
        );

        public static final Supplier<CookieItem> SPECIAL_GOOD_VISION_COOKIE = Register.item(
                "special_good_vision_cookie",
                () -> new CookieBuilder("special_good_vision_cookie").setNutrition(SPECIAL_COOKIE_NUTRITION)
                        .setSaturationModifier(SPECIAL_COOKIE_SATURATION)
                        .addEffect(MobEffects.NIGHT_VISION, 6000, 1, 1)
                        .setTooltipColor(ChatFormatting.GOLD)
                        .build()
        );

        public static final Supplier<CookieItem> FIRE_COOKIE = Register.item(
                "fire_cookie",
                () -> new CookieBuilder("fire_cookie").setNutrition(NORMAL_COOKIE_NUTRITION)
                        .setSaturationModifier(NORMAL_COOKIE_SATURATION)
                        .addEffect(MobEffects.FIRE_RESISTANCE, 1200, 0, 1)
                        .setTooltipColor(ChatFormatting.GOLD)
                        .setFireproof(true)
                        .build()
        );

        public static final Supplier<CookieItem> SPECIAL_FIRE_COOKIE = Register.item(
                "special_fire_cookie",
                () -> new CookieBuilder("special_fire_cookie").setNutrition(SPECIAL_COOKIE_NUTRITION)
                        .setSaturationModifier(SPECIAL_COOKIE_SATURATION)
                        .addEffect(MobEffects.FIRE_RESISTANCE, 2400, 1, 1)
                        .setTooltipColor(ChatFormatting.GOLD)
                        .setFireproof(true)
                        .build()
        );

        public static final Supplier<CookieItem> MELON_COOKIE = Register.item(
                "melon_cookie",
                () -> new CookieBuilder("melon_cookie").setNutrition(NORMAL_COOKIE_NUTRITION)
                        .setSaturationModifier(NORMAL_COOKIE_SATURATION)
                        .addEffect(MobEffects.HASTE, 1200, 0, 1)
                        .setTooltipColor(ChatFormatting.GREEN)
                        .build()
        );

        public static final Supplier<CookieItem> SPECIAL_MELON_COOKIE = Register.item(
                "special_melon_cookie",
                () -> new CookieBuilder("special_melon_cookie").setNutrition(SPECIAL_COOKIE_NUTRITION)
                        .setSaturationModifier(SPECIAL_COOKIE_SATURATION)
                        .addEffect(MobEffects.HASTE, 2400, 1, 1)
                        .setTooltipColor(ChatFormatting.GREEN)
                        .build()
        );

        public static final Supplier<CookieItem> APPLE_COOKIE = Register.item(
                "apple_cookie",
                () -> new CookieBuilder("apple_cookie").setTooltipColor(ChatFormatting.RED)
                        .addEffect(MobEffects.LUCK, 1200, 0, 1)
                        .build()
        );

        public static final Supplier<CookieItem> SPECIAL_APPLE_COOKIE = Register.item(
                "special_apple_cookie",
                () -> new CookieBuilder("special_apple_cookie").setNutrition(SPECIAL_COOKIE_NUTRITION)
                        .setSaturationModifier(SPECIAL_COOKIE_SATURATION)
                        .addEffect(MobEffects.LUCK, 2400, 1, 1)
                        .setTooltipColor(ChatFormatting.RED)
                        .build()
        );

        public static final Supplier<CookieItem> NETHER_WART_COOKIE = Register.item(
                "nether_wart_cookie",
                () -> new CookieBuilder("nether_wart_cookie").setNutrition(NORMAL_COOKIE_NUTRITION)
                        .setSaturationModifier(NORMAL_COOKIE_SATURATION)
                        .addEffect(MobEffects.POISON, 100, 0, 1)
                        .setTooltipColor(ChatFormatting.GOLD)
                        .build()
        );

        public static final Supplier<CookieItem> SPECIAL_NETHER_WART_COOKIE = Register.item(
                "special_nether_wart_cookie",
                () -> new CookieBuilder("special_nether_wart_cookie").setNutrition(SPECIAL_COOKIE_NUTRITION)
                        .setSaturationModifier(SPECIAL_COOKIE_SATURATION)
                        .addEffect(MobEffects.POISON, 600, 1, 1)
                        .setTooltipColor(ChatFormatting.GOLD)
                        .build()
        );

        public static final Supplier<CookieItem> GLOW_COOKIE = Register.item(
                "glow_cookie",
                () -> new CookieBuilder("glow_cookie").setNutrition(NORMAL_COOKIE_NUTRITION)
                        .setSaturationModifier(NORMAL_COOKIE_SATURATION)
                        .addEffect(MobEffects.GLOWING, 1200, 0, 1)
                        .setTooltipColor(ChatFormatting.AQUA)
                        .build()
        );

        public static final Supplier<CookieItem> SPECIAL_GLOW_COOKIE = Register.item(
                "special_glow_cookie",
                () -> new CookieBuilder("special_glow_cookie").setNutrition(SPECIAL_COOKIE_NUTRITION)
                        .setSaturationModifier(SPECIAL_COOKIE_SATURATION)
                        .addEffect(MobEffects.GLOWING, 2400, 1, 1)
                        .setTooltipColor(ChatFormatting.AQUA)
                        .build()
        );

        public static final Supplier<CookieItem> CARAMEL_COOKIE = Register.item(
                "caramel_cookie",
                () -> new CookieBuilder("caramel_cookie").setNutrition(NORMAL_COOKIE_NUTRITION)
                        .setSaturationModifier(NORMAL_COOKIE_SATURATION)
                        .addEffect(MobEffects.ABSORPTION, 200, 0, 1)
                        .setTooltipColor(ChatFormatting.AQUA)
                        .build()
        );

        public static final Supplier<CookieItem> SPECIAL_CARAMEL_COOKIE = Register.item(
                "special_caramel_cookie",
                () -> new CookieBuilder("special_caramel_cookie").setNutrition(SPECIAL_COOKIE_NUTRITION)
                        .setSaturationModifier(SPECIAL_COOKIE_SATURATION)
                        .addEffect(MobEffects.ABSORPTION, 1200, 1, 1)
                        .setTooltipColor(ChatFormatting.AQUA)
                        .build()
        );

        public static final Supplier<CookieItem> EXPLOSIVE_COOKIE = Register.item(
                "explosive_cookie",
                () -> new CookieBuilder("explosive_cookie").setNutrition(NORMAL_COOKIE_NUTRITION)
                        .setSaturationModifier(NORMAL_COOKIE_SATURATION)
                        .addEffect(MobEffects.ABSORPTION, 200, 0, 1)
                        .setTooltipColor(ChatFormatting.RED)
                        .build()
        );

        public static final Supplier<CookieItem> SPECIAL_EXPLOSIVE_COOKIE = Register.item(
                "special_explosive_cookie",
                () -> new CookieBuilder("special_explosive_cookie").setNutrition(SPECIAL_COOKIE_NUTRITION)
                        .setSaturationModifier(SPECIAL_COOKIE_SATURATION)
                        .addEffect(MobEffects.ABSORPTION, 1200, 1, 1)
                        .setTooltipColor(ChatFormatting.RED)
                        .build()
        );

        public static final Supplier<CookieItem> GOLDEN_COOKIE = Register.item(
                "golden_cookie",
                () -> new CookieBuilder("golden_cookie").setNutrition(NORMAL_COOKIE_NUTRITION)
                        .setSaturationModifier(NORMAL_COOKIE_SATURATION)
                        .addEffect(MobEffects.REGENERATION, 100, 1, 1)
                        .addEffect(MobEffects.ABSORPTION, 2400, 0, 1)
                        .setTooltipColor(ChatFormatting.YELLOW)
                        .setRarity(Rarity.RARE)
                        .build()
        );

        public static final Supplier<CookieItem> SPECIAL_GOLDEN_COOKIE = Register.item(
                "special_golden_cookie",
                () -> new CookieBuilder("special_golden_cookie").setNutrition(SPECIAL_COOKIE_NUTRITION)
                        .setSaturationModifier(SPECIAL_COOKIE_SATURATION)
                        .addEffect(MobEffects.REGENERATION, 400, 1, 1)
                        .addEffect(MobEffects.ABSORPTION, 2400, 3, 1)
                        .addEffect(MobEffects.RESISTANCE, 6000, 0, 1)
                        .addEffect(MobEffects.FIRE_RESISTANCE, 6000, 0, 1)
                        .setTooltipColor(ChatFormatting.YELLOW)
                        .setRarity(Rarity.RARE)
                        .build()
        );

        public static final Supplier<CookieItem> EVIL_COOKIE = Register.item(
                "evil_cookie",
                () -> new CookieBuilder("evil_cookie").setNutrition(NORMAL_COOKIE_NUTRITION)
                        .setSaturationModifier(NORMAL_COOKIE_SATURATION)
                        .addEffect(MobEffects.WITHER, 200, 0, 1)
                        .addEffect(MobEffects.DARKNESS, 200, 0, 1)
                        .setTooltipColor(ChatFormatting.DARK_RED)
                        .build()
        );

        public static final Supplier<CookieItem> SPECIAL_EVIL_COOKIE = Register.item(
                "special_evil_cookie",
                () -> new CookieBuilder("special_evil_cookie").setNutrition(SPECIAL_COOKIE_NUTRITION)
                        .setSaturationModifier(SPECIAL_COOKIE_SATURATION)
                        .addEffect(MobEffects.WITHER, 600, 0, 1)
                        .addEffect(MobEffects.DARKNESS, 600, 0, 1)
                        .setTooltipColor(ChatFormatting.DARK_RED)
                        .build()
        );

        public static final Supplier<CookieItem> ARISTEA_COOKIE = Register.item(
                "aristea_cookie",
                () -> new CookieBuilder("aristea_cookie").setNutrition(NORMAL_COOKIE_NUTRITION)
                        .setSaturationModifier(NORMAL_COOKIE_SATURATION)
                        .addEffect(MobEffects.LUCK, 1200, 0, 1)
                        .addEffect(MobEffects.DOLPHINS_GRACE, 1200, 0, 1)
                        .setTooltipColor(ChatFormatting.BLUE)
                        .build()
        );

        public static final Supplier<CookieItem> SPECIAL_ARISTEA_COOKIE = Register.item(
                "special_aristea_cookie",
                () -> new CookieBuilder("special_aristea_cookie").setNutrition(SPECIAL_COOKIE_NUTRITION)
                        .setSaturationModifier(SPECIAL_COOKIE_SATURATION)
                        .addEffect(MobEffects.LUCK, 2400, 1, 1)
                        .addEffect(MobEffects.DOLPHINS_GRACE, 2400, 1, 1)
                        .setTooltipColor(ChatFormatting.BLUE)
                        .build()
        );

        public static final Supplier<CookieItem> ROCKET_COOKIE = Register.item(
                "rocket_cookie",
                () -> new CookieBuilder("rocket_cookie").setNutrition(NORMAL_COOKIE_NUTRITION)
                        .setSaturationModifier(NORMAL_COOKIE_SATURATION)
                        .setTooltipColor(ChatFormatting.GOLD)
                        .build()
        );

        public static final Supplier<CookieItem> SPECIAL_ROCKET_COOKIE = Register.item(
                "special_rocket_cookie",
                () -> new CookieBuilder("special_rocket_cookie").setNutrition(SPECIAL_COOKIE_NUTRITION)
                        .setSaturationModifier(SPECIAL_COOKIE_SATURATION)
                        .setTooltipColor(ChatFormatting.GOLD)
                        .build()
        );

        public static final Supplier<CookieItem> SPOOKY_COOKIE = Register.item(
                "spooky_cookie",
                () -> new CookieBuilder("spooky_cookie").setNutrition(NORMAL_COOKIE_NUTRITION)
                        .setSaturationModifier(NORMAL_COOKIE_SATURATION)
                        .addEffect(MobEffects.DARKNESS, 200, 0, 1)
                        .setTooltipColor(ChatFormatting.GOLD)
                        .build()
        );

        public static final Supplier<CookieItem> SPECIAL_SPOOKY_COOKIE = Register.item(
                "special_spooky_cookie",
                () -> new CookieBuilder("special_spooky_cookie").setNutrition(SPECIAL_COOKIE_NUTRITION)
                        .setSaturationModifier(SPECIAL_COOKIE_SATURATION)
                        .addEffect(MobEffects.DARKNESS, 400, 1, 1)
                        .setTooltipColor(ChatFormatting.GOLD)
                        .build()
        );

        public static final Supplier<CookieItem> PECULIAR_COOKIE = Register.item(
                "peculiar_cookie",
                () -> new CookieBuilder("peculiar_cookie").setNutrition(NORMAL_COOKIE_NUTRITION)
                        .setSaturationModifier(NORMAL_COOKIE_SATURATION)
                        .addEffect(MobEffects.LEVITATION, 100, 0, 1)
                        .setTooltipColor(ChatFormatting.AQUA)
                        .build()
        );

        public static final Supplier<CookieItem> SPECIAL_PECULIAR_COOKIE = Register.item(
                "special_peculiar_cookie",
                () -> new CookieBuilder("special_peculiar_cookie").setNutrition(SPECIAL_COOKIE_NUTRITION)
                        .setSaturationModifier(SPECIAL_COOKIE_SATURATION)
                        .addEffect(MobEffects.LEVITATION, 200, 0, 1)
                        .setTooltipColor(ChatFormatting.AQUA)
                        .build()
        );

        public static final Supplier<CookieItem> PRISMATIC_COOKIE = Register.item(
                "prismatic_cookie",
                () -> new CookieBuilder("prismatic_cookie").setNutrition(NORMAL_COOKIE_NUTRITION)
                        .setSaturationModifier(NORMAL_COOKIE_SATURATION)
                        .addEffect(MobEffects.WATER_BREATHING, 1200, 0, 1)
                        .setTooltipColor(ChatFormatting.AQUA)
                        .build()
        );

        public static final Supplier<CookieItem> SPECIAL_PRISMATIC_COOKIE = Register.item(
                "special_prismatic_cookie",
                () -> new CookieBuilder("special_prismatic_cookie").setNutrition(SPECIAL_COOKIE_NUTRITION)
                        .setSaturationModifier(SPECIAL_COOKIE_SATURATION)
                        .addEffect(MobEffects.WATER_BREATHING, 2400, 0, 1)
                        .setTooltipColor(ChatFormatting.AQUA)
                        .build()
        );

        public static final Supplier<CookieItem> CHORUS_COOKIE = Register.item(
                "chorus_cookie",
                () -> new CookieBuilder("chorus_cookie").setNutrition(NORMAL_COOKIE_NUTRITION)
                        .setSaturationModifier(NORMAL_COOKIE_SATURATION)
                        .setTooltipColor(ChatFormatting.LIGHT_PURPLE)
                        .setCooldown(1f)
                        .build()
        );

        public static final Supplier<CookieItem> SPECIAL_CHORUS_COOKIE = Register.item(
                "special_chorus_cookie",
                () -> new CookieBuilder("special_chorus_cookie").setNutrition(SPECIAL_COOKIE_NUTRITION)
                        .setSaturationModifier(SPECIAL_COOKIE_SATURATION)
                        .setTooltipColor(ChatFormatting.LIGHT_PURPLE)
                        .setCooldown(1f)
                        .build()
        );

        public static final Supplier<CookieItem> BERRY_COOKIE = Register.item(
                "berry_cookie",
                () -> new CookieBuilder("berry_cookie").setNutrition(NORMAL_COOKIE_NUTRITION)
                        .setSaturationModifier(NORMAL_COOKIE_SATURATION)
                        .addEffect(MobEffects.JUMP_BOOST, 600, 1, 1)
                        .setTooltipColor(ChatFormatting.DARK_RED)
                        .build()
        );

        public static final Supplier<CookieItem> SPECIAL_BERRY_COOKIE = Register.item(
                "special_berry_cookie",
                () -> new CookieBuilder("special_berry_cookie").setNutrition(SPECIAL_COOKIE_NUTRITION)
                        .setSaturationModifier(SPECIAL_COOKIE_SATURATION)
                        .addEffect(MobEffects.JUMP_BOOST, 1200, 2, 1)
                        .setTooltipColor(ChatFormatting.DARK_RED)
                        .build()
        );

        public static final Supplier<CookieItem> OMEGA_COOKIE = Register.item(
                "omega_cookie",
                () -> new CookieBuilder("omega_cookie").setNutrition(NORMAL_COOKIE_NUTRITION)
                        .setSaturationModifier(NORMAL_COOKIE_SATURATION)
                        .addEffect(MobEffects.REGENERATION, 1200, 1, 1)
                        .addEffect(MobEffects.ABSORPTION, 1200, 1, 1)
                        .setTooltipColor(ChatFormatting.GOLD)
                        .setRarity(Rarity.EPIC)
                        .build()
        );

        public static final Supplier<CookieItem> SPECIAL_OMEGA_COOKIE = Register.item(
                "special_omega_cookie",
                () -> new CookieBuilder("special_omega_cookie").setNutrition(SPECIAL_COOKIE_NUTRITION)
                        .setSaturationModifier(SPECIAL_COOKIE_SATURATION)
                        .addEffect(MobEffects.REGENERATION, 1200, 2, 1)
                        .addEffect(MobEffects.ABSORPTION, 1200, 4, 1)
                        .setTooltipColor(ChatFormatting.GOLD)
                        .setRarity(Rarity.EPIC)
                        .build()
        );

        public static Supplier<Item> LOVE =
                Register.item("love", () -> new Love(new Item.Properties().setId(itemKey("love"))));

        private static void init() {

        }
    }

    public static class Upgrades {
        static void init() {
            WorldEvents.WORLD_LOAD.register((server, level) -> {
                Items.SPECIAL_CHOCOLATE_COOKIE.get().setUpgrade(Items.EXTRA_SPECIAL_CHOCOLATE_COOKIE);
                Items.MEDIC_COOKIE.get().setUpgrade(Items.SPECIAL_MEDIC_COOKIE);
                Items.GOOD_VISION_COOKIE.get().setUpgrade(Items.SPECIAL_GOOD_VISION_COOKIE);
                Items.FIRE_COOKIE.get().setUpgrade(Items.SPECIAL_FIRE_COOKIE);
                Items.MELON_COOKIE.get().setUpgrade(Items.SPECIAL_MELON_COOKIE);
                Items.APPLE_COOKIE.get().setUpgrade(Items.SPECIAL_APPLE_COOKIE);
                Items.NETHER_WART_COOKIE.get().setUpgrade(Items.SPECIAL_NETHER_WART_COOKIE);
                Items.GLOW_COOKIE.get().setUpgrade(Items.SPECIAL_GLOW_COOKIE);
                Items.CARAMEL_COOKIE.get().setUpgrade(Items.SPECIAL_CARAMEL_COOKIE);
                Items.EXPLOSIVE_COOKIE.get().setUpgrade(Items.SPECIAL_EXPLOSIVE_COOKIE);
                Items.GOLDEN_COOKIE.get().setUpgrade(Items.SPECIAL_GOLDEN_COOKIE);
                Items.EVIL_COOKIE.get().setUpgrade(Items.SPECIAL_EVIL_COOKIE);
                Items.ARISTEA_COOKIE.get().setUpgrade(Items.SPECIAL_ARISTEA_COOKIE);
                Items.ROCKET_COOKIE.get().setUpgrade(Items.SPECIAL_ROCKET_COOKIE);
                Items.SPOOKY_COOKIE.get().setUpgrade(Items.SPECIAL_SPOOKY_COOKIE);
                Items.PECULIAR_COOKIE.get().setUpgrade(Items.SPECIAL_PECULIAR_COOKIE);
                Items.PRISMATIC_COOKIE.get().setUpgrade(Items.SPECIAL_PRISMATIC_COOKIE);
                Items.CHORUS_COOKIE.get().setUpgrade(Items.SPECIAL_CHORUS_COOKIE);
                Items.BERRY_COOKIE.get().setUpgrade(Items.SPECIAL_BERRY_COOKIE);
                Items.OMEGA_COOKIE.get().setUpgrade(Items.SPECIAL_OMEGA_COOKIE);
            });
        }
    }

    public static class Blocks {
        public static final Supplier<Block> LOVEY_DOVEY_INFUSER = Register.block(
                "lovey_dovey_infuser",
                () -> new LoveyDoveyInfuserBlock(BlockBehaviour.Properties.ofFullCopy(net.minecraft.world.level.block.Blocks.IRON_BLOCK)
                        .setId(blockKey("lovey_dovey_infuser")))
        );

        public static final Supplier<Block> COTTON_CANDY_CROP = Register.block(
                "cotton_candy_crop",
                () -> new CottonCandyCropBlock(BlockBehaviour.Properties.ofFullCopy(net.minecraft.world.level.block.Blocks.WHEAT)
                        .setId(blockKey("cotton_candy_crop")))
        );

        public static final Supplier<Block> ARISTEA = Register.block(
                "aristea", () -> new AristeaBlock(
                        MobEffects.SPEED,
                        10,
                        BlockBehaviour.Properties.ofFullCopy(net.minecraft.world.level.block.Blocks.ALLIUM)
                                .noOcclusion()
                                .noCollision()
                                .setId(blockKey("aristea"))
                )
        );

        public static final Supplier<Block> POTTED_ARISTEA = Register.block(
                "potted_aristea", () -> new FlowerPotBlock(
                        ARISTEA.get(),
                        BlockBehaviour.Properties.ofFullCopy(net.minecraft.world.level.block.Blocks.POTTED_ALLIUM)
                                .noOcclusion()
                                .setId(blockKey("potted_aristea"))
                )
        );

        public static final Supplier<Block> COTTON_CANDY_BLOCK = Register.block(
                "cotton_candy_block",
                () -> new Block(BlockBehaviour.Properties.ofFullCopy(
                        //? if =26.2-rc-2 {
                        /*net.minecraft.world.level.block.Blocks.WOOL.white()
                        *///?} else {
                        net.minecraft.world.level.block.Blocks.WHITE_WOOL
                        //?}
                )
                        .strength(0.2f)
                        .setId(blockKey("cotton_candy_block"))) {
                    @Override
                    public void fallOn(Level level, BlockState state, BlockPos pos, Entity entity, double fallDistance) {
                        // this block is very soft, so it should not deal as much fall damage
                        entity.causeFallDamage(fallDistance, 0.2F, level.damageSources().fall());
                    }
                }
        );

        static void init() {
        }
    }

    public static class DataComponents {
        public static final Supplier<DataComponentType<ItemColorDataComponent>> COLOR =
                Register.dataComponentType("color", builder -> builder.persistent(ItemColorDataComponent.CODEC));

        static void init() {

        }
    }

    public static class Effects {
        public static final Supplier<MobEffect> SMITTEN_EFFECT = Register.mobEffect(
                "smitten", () -> new SmittenEffect(MobEffectCategory.BENEFICIAL, 0xf75477).addAttributeModifier(
                        Attributes.MOVEMENT_SPEED,
                        resource("smitten"),
                        0.15f,
                        AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
                )
        );

        static void init() {
        }
    }

    public static class Potions {
        public static Supplier<Potion> SMITTEN_POTION = Register.potion(
                "smitten_potion",
                () -> new Potion("smitten_potion", new MobEffectInstance(net.minecraft.core.Holder.direct(Effects.SMITTEN_EFFECT.get()), 1200, 0))
        );

        static void init() {
        }
    }

    public static class Tags {
        private static TagKey<Item> createItemTag(String name) {
            return TagKey.create(Registries.ITEM, Valentine.resource(name));
        }

        private static TagKey<Item> createItemTag(String namespace, String path) {
            return TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(namespace, path));
        }

        private static TagKey<Block> createBlockTag(String name) {
            return TagKey.create(Registries.BLOCK, Valentine.resource(name));
        }

        private static TagKey<Block> createBlockTag(String namespace, String path) {
            return TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(namespace, path));
        }

        // legacy tags, should be removed later (or added to datagen)
        public static class Items {
            public static final TagKey<Item> MOD_COOKIES = createItemTag("kaf_cookie");
            public static final TagKey<Item> COOKIES = createItemTag("c", "cookies");
            public static final TagKey<Item> ARISTEA = createItemTag("c", "aristeas");
            public static final TagKey<Item> FLOWERS = createItemTag("c", "flowers");
            public static final TagKey<Item> SEEDS = createItemTag("c", "seeds");
            public static final TagKey<Item> MINECRAFT_SEEDS = createItemTag("minecraft", "villager_plantable_seeds");
            public static final TagKey<Item> GOLDEN_APPLES = createItemTag("c", "golden_apples");

            // Compat
            public static final TagKey<Item> UNDIET_FOODS = createItemTag("origins", "ignore_diet");
            // public static final TagKey<Item> MEAT_FOODS = createTag("origins", "meat");
            public static final TagKey<Item> BOTANIA_SEEDS = createItemTag("botania", "seed_apothecary_reagent");
            public static final TagKey<Item> NATURALIST_SEEDS = createItemTag("naturalist", "bird_food_items");
        }
    }

    public static class Compostables {
        static void init() {
            Register.compostable((Items.COTTON_CANDY_SEEDS), 0.3f);
            Register.compostable((Blocks.ARISTEA), 0.3f);
        }
    }
}
