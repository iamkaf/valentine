package com.iamkaf.valentine.item;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public record ItemColorDataComponent(int color) {
    public static final Codec<ItemColorDataComponent> CODEC =
            RecordCodecBuilder.create(instance -> instance.group(Codec.INT.fieldOf("color")
                    .forGetter(ItemColorDataComponent::color)).apply(instance, ItemColorDataComponent::new));


    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else {
            return obj instanceof ItemColorDataComponent ex && this.color == ex.color;
        }
    }
}
