package com.iamkaf.valentine.item;

import com.iamkaf.valentine.item.custom.CookieItem;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class CookieBuilder {
    private final String id;
    private final List<MobEffectInstance> effects = new ArrayList<>();
    private final List<Float> effectProbabilities = new ArrayList<>();
    private int nutrition = 1;
    private float saturationModifier = 0.1f;
    private Rarity rarity = Rarity.COMMON;
    private ChatFormatting tooltipColor = ChatFormatting.AQUA;
    private boolean fireproof = false;

    public CookieBuilder(String id) {
        this.id = id;
    }

    public CookieBuilder setNutrition(int nutrition) {
        this.nutrition = nutrition;
        return this;
    }

    public CookieBuilder setSaturationModifier(float saturationModifier) {
        this.saturationModifier = saturationModifier;
        return this;
    }

    public CookieBuilder addEffect(Holder<MobEffect> effect, int duration, int amplifier, float probability) {
        this.effects.add(new MobEffectInstance(effect, duration, amplifier));
        this.effectProbabilities.add(probability);
        return this;
    }

    public CookieBuilder setTooltipColor(ChatFormatting tooltipColor) {
        this.tooltipColor = tooltipColor;
        return this;
    }

    public CookieBuilder setRarity(Rarity rarity) {
        this.rarity = rarity;
        return this;
    }

    public CookieBuilder setFireproof(boolean fireproof) {
        this.fireproof = fireproof;
        return this;
    }

    public CookieItem build() {
        Item.Properties properties =
                new Item.Properties().food(makeFoodComponent()).stacksTo(16).rarity(rarity);
        if (fireproof) {
            properties = properties.fireResistant();
        }
        return new CookieItem(properties, id, tooltipColor);
    }

    private FoodProperties makeFoodComponent() {
        FoodProperties.Builder foodBuilder = new FoodProperties.Builder().nutrition(nutrition)
                .saturationModifier(saturationModifier)
                .fast()
                .alwaysEdible();

        for (int i = 0; i < effects.size(); i++) {
            foodBuilder.effect(effects.get(i), effectProbabilities.get(i));
        }

        return foodBuilder.build();
    }
}
