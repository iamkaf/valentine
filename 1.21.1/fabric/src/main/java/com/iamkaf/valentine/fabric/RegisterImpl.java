package com.iamkaf.valentine.fabric;

import com.iamkaf.valentine.Valentine;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.*;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class RegisterImpl {
    public static <T extends Block> Supplier<T> block(String id, Supplier<T> supplier) {
        var obj = Registry.register(BuiltInRegistries.BLOCK, Valentine.resource(id), supplier.get());
        // TODO: i need to find a better way to do this in the future
        if (!id.contains("crop")) {
            item(id, () -> new BlockItem(obj, new Item.Properties()));
        }
        return () -> obj;
    }

    public static <T extends Item> Supplier<T> item(String id, Supplier<T> supplier) {
        var obj = Registry.register(BuiltInRegistries.ITEM, Valentine.resource(id), supplier.get());
        return () -> obj;
    }

    public static <T extends Item> void fuelItem(Supplier<T> supplier, int burnTime) {
        FuelRegistry.INSTANCE.add(supplier.get(), burnTime);
    }

    public static Supplier<CreativeModeTab> creativeModeTab(Supplier<? extends Item> icon, String tabName) {
        var obj = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
                Valentine.resource(tabName),
                FabricItemGroup.builder()
                        .icon(() -> new ItemStack(icon.get()))
                        .title(Component.translatable("creativetab." + Valentine.MOD_ID + "." + tabName))
                        .displayItems((itemDisplayParameters, output) -> {
                            for (var item : Valentine.CreativeModeTabs.getCreativeModeTabItems()) {
                                output.accept(item);
                            }
                        })
                        .build()
        );
        return () -> obj;
    }

    public static <T> Supplier<DataComponentType<T>> dataComponentType(String name,
            UnaryOperator<DataComponentType.Builder<T>> builderOperator) {
        var obj = Registry.register(BuiltInRegistries.DATA_COMPONENT_TYPE,
                Valentine.resource(name),
                builderOperator.apply(DataComponentType.builder()).build()
        );
        return () -> obj;
    }

    public static Holder<ArmorMaterial> armorMaterial(String name, ArmorMaterial material) {
        var obj = Registry.registerForHolder(BuiltInRegistries.ARMOR_MATERIAL,
                Valentine.resource(name),
                material
        );
        return obj;
    }

    public static <T extends ItemLike> void compostable(Supplier<T> item, float chance) {
        CompostingChanceRegistry.INSTANCE.add(item.get(), chance);
    }

    public static Holder<MobEffect> mobEffect(String id, Supplier<MobEffect> effect) {
        return Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT, Valentine.resource(id), effect.get());
    }

    public static Holder<Potion> potion(String id, Supplier<Potion> potion) {
        return Registry.registerForHolder(BuiltInRegistries.POTION, Valentine.resource(id), potion.get());
    }
}
