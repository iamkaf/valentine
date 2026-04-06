package com.iamkaf.valentine;

import com.iamkaf.amber.api.registry.v1.DeferredRegister;
import com.iamkaf.amber.api.registry.v1.RegistrySupplier;
import com.iamkaf.amber.api.registry.v1.creativetabs.CreativeModeTabRegistry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public final class Register {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(Valentine.MOD_ID, Registries.BLOCK);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Valentine.MOD_ID, Registries.ITEM);
    public static final DeferredRegister<DataComponentType<?>> DATA_COMPONENT_TYPES =
            DeferredRegister.create(Valentine.MOD_ID, Registries.DATA_COMPONENT_TYPE);
    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(Valentine.MOD_ID, Registries.MOB_EFFECT);
    public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(Valentine.MOD_ID, Registries.POTION);

    private static final Map<Supplier<? extends ItemLike>, Float> COMPOSTABLES = new LinkedHashMap<>();

    private Register() {}

    public static <T extends Block> Supplier<T> block(String id, Supplier<T> supplier) {
        RegistrySupplier<T> block = BLOCKS.register(id, supplier);
        if (!id.contains("crop")) {
            item(id, itemKey -> new net.minecraft.world.item.BlockItem(
                    block.get(),
                    new Item.Properties().setId(itemKey).useBlockDescriptionPrefix()
            ));
        }
        return block;
    }

    public static <T extends Item> Supplier<T> item(String id, Supplier<T> supplier) {
        return ITEMS.register(id, supplier);
    }

    public static <T extends Item> Supplier<T> item(String id, java.util.function.Function<net.minecraft.resources.ResourceKey<Item>, T> supplier) {
        return ITEMS.register(id, supplier);
    }

    public static RegistrySupplier<CreativeModeTab> creativeModeTab(Supplier<? extends Item> icon, String tabName) {
        return CreativeModeTabRegistry.register(
                CreativeModeTabRegistry.builder(Valentine.resource(tabName))
                        .title(Component.translatable("creativetab." + Valentine.MOD_ID + "." + tabName))
                        .icon(() -> new ItemStack(icon.get()))
        );
    }

    public static <T> Supplier<DataComponentType<T>> dataComponentType(
            String name,
            UnaryOperator<DataComponentType.Builder<T>> builderUnaryOperator
    ) {
        return DATA_COMPONENT_TYPES.register(name, () -> builderUnaryOperator.apply(DataComponentType.builder()).build());
    }

    public static <T extends ItemLike> void compostable(Supplier<T> item, float chance) {
        COMPOSTABLES.put(item, chance);
    }

    public static Map<Supplier<? extends ItemLike>, Float> compostables() {
        return COMPOSTABLES;
    }

    public static Supplier<MobEffect> mobEffect(String id, Supplier<MobEffect> effect) {
        return MOB_EFFECTS.register(id, effect);
    }

    public static Supplier<Potion> potion(String id, Supplier<Potion> potion) {
        return POTIONS.register(id, potion);
    }

    public static void init() {
        BLOCKS.register();
        ITEMS.register();
        DATA_COMPONENT_TYPES.register();
        MOB_EFFECTS.register();
        POTIONS.register();
    }
}
