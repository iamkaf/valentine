package com.iamkaf.valentine.item.fabric;

import com.mojang.serialization.MapCodec;
import net.minecraft.client.renderer.item.properties.numeric.RangeSelectItemModelProperties;
import net.minecraft.client.renderer.item.properties.numeric.RangeSelectItemModelProperty;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;

public class CustomItemPropertiesImpl {
    public static void register(Item item, Identifier name,
            MapCodec<? extends RangeSelectItemModelProperty> property) {
//        ItemProperties.register(item, name, property);
        RangeSelectItemModelProperties.ID_MAPPER.put(name, property);
    }
}
