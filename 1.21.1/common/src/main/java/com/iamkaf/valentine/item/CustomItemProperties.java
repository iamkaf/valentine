package com.iamkaf.valentine.item;

import com.iamkaf.valentine.Valentine;
import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.client.renderer.item.ClampedItemPropertyFunction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public class CustomItemProperties {
    public static void init() {
        register(
                Valentine.Items.COLORFUL_COTTON_CANDY.get(),
                Valentine.resource("color"),
                (stack, world, entity, seed) -> {
                    var component = stack.get(Valentine.DataComponents.COLOR.get());
                    if (component == null) {
                        return 0f;
                    }
                    return (float) component.color() / 10;
                }
        );
    }

    @ExpectPlatform
    public static void register(Item item, ResourceLocation name, ClampedItemPropertyFunction property) {

    }
}
