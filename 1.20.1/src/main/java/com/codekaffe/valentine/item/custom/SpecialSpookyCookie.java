package com.codekaffe.valentine.item.custom;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;

public class SpecialSpookyCookie extends SpookyCookie {

    public static final FoodComponent FOOD_COMPONENT = new FoodComponent.Builder()
            .hunger(4)
            .saturationModifier(0.2f)
            .alwaysEdible()
            .statusEffect(new StatusEffectInstance(StatusEffects.DARKNESS, 400, 1), 1f)
            .build();

    public SpecialSpookyCookie(Settings settings) {
        super(settings);
    }
}
