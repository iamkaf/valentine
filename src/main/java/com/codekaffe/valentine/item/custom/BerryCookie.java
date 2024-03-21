package com.codekaffe.valentine.item.custom;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.BatEntity;
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

public class BerryCookie extends Item {

    public static final FoodComponent FOOD_COMPONENT = new FoodComponent.Builder()
            .hunger(1)
            .saturationModifier(0.1f)
            .alwaysEdible()
            .statusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, 600), 1f)
            .build();

    public BerryCookie(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(
            ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context
    ) {
        tooltip.add(Text.literal("You're looking berry cute today.").formatted(Formatting.DARK_RED));
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        world.playSound(
                user.getX(),
                user.getY(),
                user.getZ(),
                SoundEvents.ENTITY_FOX_AMBIENT,
                SoundCategory.PLAYERS,
                1f,
                1f,
                false
        );

        return super.finishUsing(stack, world, user);
    }
}
