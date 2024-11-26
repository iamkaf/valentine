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
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class PeculiarCookie extends Item {

    public static final FoodComponent FOOD_COMPONENT = new FoodComponent.Builder()
            .hunger(1)
            .saturationModifier(0.1f)
            .alwaysEdible()
            .statusEffect(new StatusEffectInstance(StatusEffects.LEVITATION, 100), 1f)
            .build();

    public PeculiarCookie(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(
            ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context
    ) {
        tooltip.add(Text.literal("You take my head to the clouds.").formatted(Formatting.AQUA));
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (world instanceof ServerWorld serverWorld) {
            serverWorld.spawnParticles(
                    ParticleTypes.CLOUD,
                    user.getX(),
                    user.getY() + 1,
                    user.getZ(),
                    100,
                    1,
                    1,
                    1,
                    0.5D
            );
        }
        return super.finishUsing(stack, world, user);
    }
}
