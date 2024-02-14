package com.codekaffe.valentine.util;

import com.codekaffe.valentine.KafValentine;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModTags {
    public static class Blocks {

        private static TagKey<Block> createTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, new Identifier(KafValentine.MOD_ID, name));
        }
    }

    public static class Items {
        public static final TagKey<Item> SPECIAL_COOKIE = createTag("special_cookie");

        private static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, new Identifier(KafValentine.MOD_ID, name));
        }
    }
}
