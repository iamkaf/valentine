package com.codekaffe.valentine.item.custom;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SpecialMelonCookie extends Item {

    public static final FoodComponent FOOD_COMPONENT = new FoodComponent.Builder()
            .hunger(4)
            .saturationModifier(0.2f)
            .alwaysEdible()
            .statusEffect(new StatusEffectInstance(StatusEffects.HASTE, 2400, 1), 1f)
            .build();

    public SpecialMelonCookie(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(
            ItemStack stack,
            @Nullable World world,
            List<Text> tooltip,
            TooltipContext context
    ) {
        tooltip.add(Text.literal("Refreshing!").formatted(Formatting.GREEN));
    }
}
