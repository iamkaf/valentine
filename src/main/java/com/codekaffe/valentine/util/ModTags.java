package com.codekaffe.valentine.util;

import com.codekaffe.valentine.KafValentine;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> FLOWERS = createCommonTag("flowers");
        public static final TagKey<Block> PLANTS = createCommonTag("plant");

        private static TagKey<Block> createTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, new Identifier(KafValentine.MOD_ID, name));
        }

        private static TagKey<Block> createCommonTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, new Identifier("c", name));
        }
    }

    public static class Items {
        public static final TagKey<Item> MOD_COOKIES = createModTag("kaf_cookie");
        public static final TagKey<Item> COOKIES = createCommonTag("cookies");
        public static final TagKey<Item> ARISTEA = createCommonTag("aristeas");
        public static final TagKey<Item> FLOWERS = createCommonTag("flowers");
        public static final TagKey<Item> SEEDS = createCommonTag("seeds");
        public static final TagKey<Item> MINECRAFT_SEEDS = createTag("minecraft",
                "villager_plantable_seeds"
        );
        public static final TagKey<Item> GOLDEN_APPLES = createCommonTag("golden_apples");

        // Compat
        public static final TagKey<Item> UNDIET_FOODS = createTag("origins", "ignore_diet");
        // public static final TagKey<Item> MEAT_FOODS = createTag("origins", "meat");

        public static final TagKey<Item> BOTANIA_SEEDS = createTag("botania",
                "seed_apothecary_reagent"
        );

        public static final TagKey<Item> NATURALIST_SEEDS = createTag("naturalist",
                "bird_food_items"
        );

        private static TagKey<Item> createModTag(String name) {
            return createTag(KafValentine.MOD_ID, name);
        }

        private static TagKey<Item> createCommonTag(String name) {
            return createTag("c", name);
        }

        private static TagKey<Item> createTag(String namespace, String name) {
            return TagKey.of(RegistryKeys.ITEM, new Identifier(namespace, name));
        }
    }
}
