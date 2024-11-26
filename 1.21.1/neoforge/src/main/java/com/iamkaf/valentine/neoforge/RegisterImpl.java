package com.iamkaf.valentine.neoforge;

import com.iamkaf.valentine.Valentine;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.*;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class RegisterImpl {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.createBlocks(Valentine.MOD_ID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.createItems(Valentine.MOD_ID);
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Valentine.MOD_ID);
    public static final DeferredRegister<DataComponentType<?>> DATA_COMPONENT_TYPES =
            DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, Valentine.MOD_ID);
    public static final DeferredRegister<ArmorMaterial> ARMOR_MATERIAL = DeferredRegister.create(Registries.ARMOR_MATERIAL, Valentine.MOD_ID);
    public static final DeferredRegister<MobEffect> MOB_EFFECT = DeferredRegister.create(Registries.MOB_EFFECT, Valentine.MOD_ID);
    public static final DeferredRegister<Potion> POTION = DeferredRegister.create(Registries.POTION, Valentine.MOD_ID);

    public static <T extends Block> Supplier<T> block(String id, Supplier<T> supplier) {
        var block = BLOCKS.register(id, supplier);
        // TODO: i need to find a better way to do this in the future
        if (!id.contains("crop")) {
            item(id, () -> new BlockItem(block.get(), new Item.Properties()));
        }
        return block;
    }

    public static <T extends Item> Supplier<T> item(String id, Supplier<T> supplier) {
        return ITEMS.register(id, supplier);
    }

    public static <T extends Item> void fuelItem(Supplier<T> supplier, int burnTime) {
        // NO-OP, done in datapack
    }

    public static Supplier<CreativeModeTab> creativeModeTab(Supplier<? extends Item> icon, String tabName) {
        var tab = TABS.register(tabName,
                () -> CreativeModeTab.builder().icon(() -> new ItemStack(icon.get()))
                        .title(Component.translatable("creativetab." + Valentine.MOD_ID + "." + tabName))
                        .displayItems((itemDisplayParameters, output) -> {
                            for (var item : Valentine.CreativeModeTabs.getCreativeModeTabItems()) {
                                output.accept(item);
                            }
                        }).build()
        );

        return () -> tab.get();
    }

    public static <T> Supplier<DataComponentType<T>> dataComponentType(String name, UnaryOperator<DataComponentType.Builder<T>> builderOperator) {
        return DATA_COMPONENT_TYPES.register(name, () -> builderOperator.apply(DataComponentType.builder()).build());
    }

    public static Holder<ArmorMaterial> armorMaterial(String name, ArmorMaterial material) {
        var obj = Registry.registerForHolder(BuiltInRegistries.ARMOR_MATERIAL, Valentine.resource(name), material);
        return obj;
    }

    public static <T extends ItemLike> void compostable(Supplier<T> item, float chance) {
        // NO-OP, done in datapack
    }

    public static Holder<MobEffect> mobEffect(String id, Supplier<MobEffect> effect) {
        return MOB_EFFECT.register(id, effect);
    }

    public static Holder<Potion> potion(String id, Supplier<Potion> potion) {
        return POTION.register(id, potion);
    }
}
