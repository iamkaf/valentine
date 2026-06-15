package com.iamkaf.valentine.item;

import com.iamkaf.valentine.Valentine;
import com.mojang.serialization.MapCodec;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.item.properties.numeric.RangeSelectItemModelProperty;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.ItemOwner;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public class CustomItemProperties {
    public static void init() {
        // Historical example of how to do this.
//        register(Valentine.Items.COLORFUL_COTTON_CANDY.get(), Valentine.resource("color"), CottonCandyColor.MAP_CODEC);
    }

    public static void register(Item item, Identifier name,
            MapCodec<? extends RangeSelectItemModelProperty> property) {
    }

    public record CottonCandyColor() implements RangeSelectItemModelProperty {
        public static final MapCodec<CottonCandyColor> MAP_CODEC = MapCodec.unit(new CottonCandyColor());
        public static final Identifier ID = Valentine.resource("color");

        @Override
        public float get(ItemStack stack, @Nullable ClientLevel level, @Nullable ItemOwner owner, int seed) {
            var component = stack.get(Valentine.DataComponents.COLOR.get());
            if (component == null) {
                return 0f;
            }
            return (float) component.color() / 10;
        }

        @Override
        public MapCodec<CottonCandyColor> type() {
            return MAP_CODEC;
        }
    }
}
