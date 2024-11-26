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

public class SpookyCookie extends Item {

    public static final FoodComponent FOOD_COMPONENT = new FoodComponent.Builder()
            .hunger(1)
            .saturationModifier(0.1f)
            .alwaysEdible()
            .statusEffect(new StatusEffectInstance(StatusEffects.DARKNESS, 200), 1f)
            .build();

    public SpookyCookie(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(
            ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context
    ) {
        tooltip.add(Text.literal("Spooky!").formatted(Formatting.GOLD));
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (world instanceof ServerWorld serverWorld) {
            serverWorld.spawnParticles(
                    ParticleTypes.WITCH,
                    user.getX(),
                    user.getY() + 1,
                    user.getZ(),
                    100,
                    1,
                    1,
                    1,
                    0.5D
            );
            var bat = new BatEntity(EntityType.BAT, serverWorld);
            bat.setPos(user.getX(), user.getY(), user.getZ());
            serverWorld.spawnEntity(bat);
        }
        world.playSound(
                user.getX(),
                user.getY(),
                user.getZ(),
                SoundEvents.ENTITY_GHAST_SCREAM,
                SoundCategory.PLAYERS,
                1f,
                0.8f,
                false
        );
        world.playSound(
                user.getX(),
                user.getY(),
                user.getZ(),
                SoundEvents.ENTITY_GHAST_SHOOT,
                SoundCategory.PLAYERS,
                1f,
                0.5f,
                false
        );

        return super.finishUsing(stack, world, user);
    }
}
