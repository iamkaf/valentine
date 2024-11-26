package com.iamkaf.valentine;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class Register {
    @ExpectPlatform
    public static <T extends Block> Supplier<T> block(String id, Supplier<T> supplier) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static <T extends Item> Supplier<T> item(String id, Supplier<T> supplier) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static Supplier<CreativeModeTab> creativeModeTab(Supplier<? extends Item> icon, String tabName) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static <T extends Item> void fuelItem(Supplier<T> supplier, int burnTime) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static <T> Supplier<DataComponentType<T>> dataComponentType(String name,
            UnaryOperator<DataComponentType.Builder<T>> builderUnaryOperator) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static Holder<ArmorMaterial> armorMaterial(String name, ArmorMaterial material) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static <T extends ItemLike> void compostable(Supplier<T> item, float chance) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static Holder<MobEffect> mobEffect(String id, Supplier<MobEffect> effect) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static Holder<Potion> potion(String id, Supplier<Potion> potion) {
        throw new AssertionError();
    }
}
