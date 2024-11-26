package com.iamkaf.valentine.events;

import com.iamkaf.valentine.Valentine;
import com.mojang.logging.LogUtils;
import it.unimi.dsi.fastutil.ints.IntList;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ambient.Bat;
import net.minecraft.world.entity.animal.Fox;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.FireworkExplosion;
import net.minecraft.world.item.component.Fireworks;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import org.slf4j.Logger;

import java.util.List;

public class OnCookieEaten {
    private static final Logger LOGGER = LogUtils.getLogger();

    public static void execute(String id, ItemStack foodItemStack, Player user, Level world) {
        switch (id) {
            case "aristea_cookie":
            case "special_aristea_cookie":
                aristeaCookie(user, world);
                break;
            case "berry_cookie":
            case "special_berry_cookie":
                berryCookie(user, world);
                break;
            case "chorus_cookie":
            case "special_chorus_cookie":
                chorusCookie(foodItemStack, user, world);
                break;
            case "explosive_cookie":
                explosiveCookie(user, world, false);
                break;
            case "special_explosive_cookie":
                explosiveCookie(user, world, true);
                break;
            case "omega_cookie":
                omegaCookie(user, world, false);
                break;
            case "special_omega_cookie":
                omegaCookie(user, world, true);
                break;
            case "peculiar_cookie":
            case "special_peculiar_cookie":
                peculiarCookie(user, world);
                break;
            case "prismatic_cookie":
            case "special_prismatic_cookie":
                prismaticCookie(user, world);
                break;
            case "rocket_cookie":
                rocketCookie(user, world, false);
                break;
            case "special_rocket_cookie":
                rocketCookie(user, world, true);
                break;
            case "spooky_cookie":
            case "special_spooky_cookie":
                spookyCookie(user, world);
                break;
            default:
                // do nothing.
        }

        LOGGER.info("A cookie has been eaten! {} {}", user.getName(), foodItemStack.toString());
    }

    private static void berryCookie(Player user, Level world) {
        world.playLocalSound(
                user.getX(),
                user.getY(),
                user.getZ(),
                SoundEvents.FOX_AMBIENT,
                SoundSource.PLAYERS,
                1f,
                1f,
                false
        );
    }

    private static void aristeaCookie(Player user, Level world) {
        if (world instanceof ServerLevel serverLevel) {
            serverLevel.sendParticles(
                    ParticleTypes.HEART,
                    user.getX(),
                    user.getY() + 1,
                    user.getZ(),
                    10,
                    0.5,
                    0.2,
                    0.5,
                    0.8d
            );
        }
    }

    private static void chorusCookie(ItemStack itemStack, LivingEntity livingEntity, Level world) {
        if (!world.isClientSide) {
            for (int i = 0; i < 16; i++) {
                double d = livingEntity.getX() + (livingEntity.getRandom().nextDouble() - 0.5) * 16.0;
                double e =
                        Mth.clamp(livingEntity.getY() + (double) (livingEntity.getRandom().nextInt(16) - 8),
                                (double) world.getMinBuildHeight(),
                                (double) (world.getMinBuildHeight() + ((ServerLevel) world).getLogicalHeight() - 1)
                        );
                double f = livingEntity.getZ() + (livingEntity.getRandom().nextDouble() - 0.5) * 16.0;
                if (livingEntity.isPassenger()) {
                    livingEntity.stopRiding();
                }

                Vec3 vec3 = livingEntity.position();
                if (livingEntity.randomTeleport(d, e, f, true)) {
                    world.gameEvent(GameEvent.TELEPORT, vec3, GameEvent.Context.of(livingEntity));
                    SoundSource soundSource;
                    SoundEvent soundEvent;
                    if (livingEntity instanceof Fox) {
                        soundEvent = SoundEvents.FOX_TELEPORT;
                        soundSource = SoundSource.NEUTRAL;
                    } else {
                        soundEvent = SoundEvents.CHORUS_FRUIT_TELEPORT;
                        soundSource = SoundSource.PLAYERS;
                    }

                    world.playSound(
                            null,
                            livingEntity.getX(),
                            livingEntity.getY(),
                            livingEntity.getZ(),
                            soundEvent,
                            soundSource
                    );
                    livingEntity.resetFallDistance();
                    break;
                }
            }

            if (livingEntity instanceof Player player) {
                player.resetCurrentImpulseContext();
                player.getCooldowns().addCooldown(Valentine.Items.CHORUS_COOKIE.get(), 20);
                player.getCooldowns().addCooldown(Valentine.Items.SPECIAL_CHORUS_COOKIE.get(), 20);
                player.getCooldowns().addCooldown(Items.CHORUS_FRUIT, 20);
            }
        }
    }

    private static void explosiveCookie(Player user, Level world, boolean isSpecial) {
        // gg
        if (!world.isClientSide) {
            world.explode(
                    null,
                    user.getX(),
                    user.getY(),
                    user.getZ(),
                    isSpecial ? 40f : 10f,
                    false,
                    Level.ExplosionInteraction.BLOCK
            );
        }
    }

    private static void omegaCookie(Player user, Level world, boolean isSpecial) {
        if (world instanceof ServerLevel serverWorld) {
            // clear weather
            serverWorld.resetWeatherCycle();

            // send message to server
            serverWorld.getServer().sendSystemMessage(getMessage(user));

            var players = serverWorld.players();
            for (var player : players) {
                // play sound
                player.playSound(SoundEvents.TOTEM_USE);
                // spawn totem particles
                serverWorld.sendParticles(
                        ParticleTypes.TOTEM_OF_UNDYING,
                        user.getX(),
                        user.getY() + 1,
                        user.getZ(),
                        1000,
                        0.5,
                        0.2,
                        0.5,
                        0.5d
                );
                // send message
                player.sendSystemMessage(getMessage(user));
                // give effects
                player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 1200, isSpecial ? 2 : 1));
                player.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 1200, isSpecial ? 4 : 1));
            }
        }
    }

    private static Component getMessage(LivingEntity user) {
        return Component.literal(user.getName().getString() + " used an Omega Cookie!")
                .withStyle(ChatFormatting.GOLD, ChatFormatting.BOLD);
    }

    private static void peculiarCookie(Player user, Level world) {
        if (world instanceof ServerLevel serverLevel) {
            serverLevel.sendParticles(
                    ParticleTypes.CLOUD,
                    user.getX(),
                    user.getY() + 1,
                    user.getZ(),
                    100,
                    0.5,
                    0.2,
                    0.5,
                    0.8d
            );
        }
    }

    private static void prismaticCookie(Player user, Level world) {
        world.playLocalSound(
                user.getX(),
                user.getY(),
                user.getZ(),
                SoundEvents.BREWING_STAND_BREW,
                SoundSource.PLAYERS,
                1f,
                1f,
                false
        );
        if (world instanceof ServerLevel serverLevel) {
            serverLevel.sendParticles(
                    ParticleTypes.BUBBLE,
                    user.getX(),
                    user.getY() + 1,
                    user.getZ(),
                    100,
                    0.5,
                    0.2,
                    0.5,
                    0.8d
            );
        }
    }

    private static void rocketCookie(Player user, Level world, boolean isSpecial) {
        if (!world.isClientSide) {
            RandomSource random = user.getRandom();
            DyeColor dyeColor = Util.getRandom(DyeColor.values(), random);

            ItemStack itemStack = new ItemStack(Items.FIREWORK_ROCKET);
            itemStack.set(DataComponents.FIREWORKS,
                    new Fireworks((byte) (isSpecial ? 2 : 0), List.of(new FireworkExplosion(
                            isSpecial ? FireworkExplosion.Shape.LARGE_BALL :
                                    FireworkExplosion.Shape.SMALL_BALL,
                            IntList.of(dyeColor.getFireworkColor()),
                            IntList.of(),
                            false,
                            false
                    )))
            );

            FireworkRocketEntity fireworkRocketEntity = new FireworkRocketEntity(world,
                    user,
                    user.getX(),
                    user.getEyeY(),
                    user.getZ(),
                    itemStack
            );
            world.addFreshEntity(fireworkRocketEntity);
        }
    }

    private static void spookyCookie(Player user, Level world) {
        world.playLocalSound(
                user.getX(),
                user.getY(),
                user.getZ(),
                SoundEvents.GHAST_SCREAM,
                SoundSource.PLAYERS,
                1f,
                0.8f,
                false
        );
        world.playLocalSound(
                user.getX(),
                user.getY(),
                user.getZ(),
                SoundEvents.GHAST_SHOOT,
                SoundSource.PLAYERS,
                1f,
                0.5f,
                false
        );
        if (world instanceof ServerLevel serverLevel) {
            serverLevel.sendParticles(
                    ParticleTypes.WITCH,
                    user.getX(),
                    user.getY() + 1,
                    user.getZ(),
                    100,
                    0.5,
                    0.2,
                    0.5,
                    0.8d
            );
            var bat = new Bat(EntityType.BAT, serverLevel);
            bat.setPos(user.getX(), user.getY(), user.getZ());
            serverLevel.addFreshEntity(bat);
        }
    }
}
