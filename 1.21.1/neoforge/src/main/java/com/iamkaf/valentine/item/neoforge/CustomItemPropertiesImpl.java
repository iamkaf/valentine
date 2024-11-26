package com.iamkaf.valentine.item.neoforge;

import net.minecraft.client.renderer.item.ClampedItemPropertyFunction;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public class CustomItemPropertiesImpl {
    public static void register(Item item, ResourceLocation name, ClampedItemPropertyFunction property) {
        ItemProperties.register(item, name, property);
    }
}
