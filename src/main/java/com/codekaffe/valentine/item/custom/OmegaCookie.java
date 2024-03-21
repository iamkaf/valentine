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

public class OmegaCookie extends Item {

    private static StatusEffectInstance EFFECT_PRIMARY = new StatusEffectInstance(StatusEffects.REGENERATION,
            1200,
            1
    );
    private static StatusEffectInstance EFFECT_SECONDARY = new StatusEffectInstance(StatusEffects.ABSORPTION,
            1200,
            1
    );
    public static final FoodComponent FOOD_COMPONENT = new FoodComponent.Builder()
            .hunger(1)
            .saturationModifier(0.1f)
            .alwaysEdible()
            .statusEffect(EFFECT_PRIMARY, 1f)
            .statusEffect(EFFECT_SECONDARY, 1f)
            .build();

    public OmegaCookie(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(
            ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context
    ) {
        tooltip.add(Text
                .literal("You are what I hold dearest.")
                .formatted(Formatting.YELLOW));
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (world instanceof ServerWorld serverWorld) {
            // clear weather
            serverWorld.setWeather(0, 0, false, false);

            // send message to server
            serverWorld.getServer().sendMessage(getMessage(user));

            var players = serverWorld.getPlayers();
            for (var player : players) {
                // play sound
                player.playSound(SoundEvents.ITEM_TOTEM_USE, SoundCategory.MASTER, 1f, 1f);
                // spawn totem particles
                serverWorld.spawnParticles(ParticleTypes.TOTEM_OF_UNDYING,
                        player.getX(),
                        player.getY() + 1,
                        player.getZ(),
                        1000,
                        1,
                        1,
                        1,
                        0.5D
                );
                // send message
                player.sendMessage(getMessage(user));
                // give effects
                player.addStatusEffect(EFFECT_PRIMARY);
                player.addStatusEffect(EFFECT_SECONDARY);
            }
        }
        return super.finishUsing(stack, world, user);
    }

    protected Text getMessage(LivingEntity user) {
        return Text
                .literal(user.getName().getString() + " used an Omega Cookie!")
                .formatted(Formatting.GOLD)
                .formatted(Formatting.BOLD);
    }
}
