package com.codekaffe.valentine.item.custom;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SpecialOmegaCookie extends OmegaCookie {

    private static StatusEffectInstance EFFECT_PRIMARY = new StatusEffectInstance(StatusEffects.REGENERATION,
            1200,
            2
    );
    private static StatusEffectInstance EFFECT_SECONDARY = new StatusEffectInstance(StatusEffects.ABSORPTION,
            1200,
            4
    );
    public static final FoodComponent FOOD_COMPONENT = new FoodComponent.Builder()
            .hunger(1)
            .saturationModifier(0.1f)
            .alwaysEdible()
            .statusEffect(EFFECT_PRIMARY, 1f)
            .statusEffect(EFFECT_SECONDARY, 1f)
            .build();

    public SpecialOmegaCookie(Settings settings) {
        super(settings);
    }

    @Override
    protected Text getMessage(LivingEntity user) {
        return Text
                .literal(user.getName().getString() + " used a Special Omega Cookie!!!")
                .formatted(Formatting.GOLD)
                .formatted(Formatting.BOLD);
    }
}
