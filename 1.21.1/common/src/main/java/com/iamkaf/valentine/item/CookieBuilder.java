package com.iamkaf.valentine.item;

import com.iamkaf.valentine.Valentine;
import com.iamkaf.valentine.item.custom.CookieItem;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;

import java.util.ArrayList;
import java.util.List;

public class CookieBuilder {
    private final String id;
    private final List<MobEffectInstance> effects = new ArrayList<>();
    private final List<Float> effectProbabilities = new ArrayList<>();
    private int nutrition = 1;
    private float saturationModifier = 0.1f;
    private Rarity rarity = Rarity.COMMON;
    private ChatFormatting tooltipColor = ChatFormatting.AQUA;
    private boolean fireproof = false;
    private float cooldown;

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

    public CookieBuilder setCooldown(float cooldownSeconds) {
        this.cooldown = cooldownSeconds;
        return this;
    }

    public CookieItem build() {
        Item.Properties properties =
                new Item.Properties().food(makeFoodComponent(), makeConsumableComponent()).stacksTo(16).rarity(rarity);
        if (fireproof) {
            properties = properties.fireResistant();
        }
        if (cooldown > 0f) {
            properties = properties.useCooldown(cooldown);
        }

        ResourceKey<Item> itemId = ResourceKey.create(Registries.ITEM, Valentine.resource(id));

        return new CookieItem(properties.setId(itemId), id, tooltipColor);
    }

    private Consumable makeConsumableComponent() {
        return Consumables.defaultFood()
                .onConsume(new ApplyStatusEffectsConsumeEffect(effects))
                .consumeSeconds(0.8F)
                .build();
    }

    private FoodProperties makeFoodComponent() {
        FoodProperties.Builder foodBuilder =
                new FoodProperties.Builder().nutrition(nutrition).saturationModifier(saturationModifier).alwaysEdible();

        return foodBuilder.build();
    }
}
