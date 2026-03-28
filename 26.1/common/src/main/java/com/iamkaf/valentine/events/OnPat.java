package com.iamkaf.valentine.events;

import com.iamkaf.amber.api.event.v1.events.common.PlayerEvents;
import com.iamkaf.valentine.Valentine;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.game.ClientboundSoundPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class OnPat {
    public static void init() {
        PlayerEvents.ENTITY_INTERACT.register(OnPat::onInteractEntity);
    }

    private static InteractionResult onInteractEntity(Player player, net.minecraft.world.level.Level level, InteractionHand hand, Entity entity) {
        if (!hand.equals(InteractionHand.MAIN_HAND)) {
            return InteractionResult.PASS;
        }

        if (!player.getItemInHand(hand).isEmpty() || !player.isShiftKeyDown()) {
            return InteractionResult.PASS;
        }

        if (player instanceof ServerPlayer serverPlayer && entity instanceof ServerPlayer other) {
            ServerLevel serverLevel = (ServerLevel) serverPlayer.level();
            patVFX(other);
            var love = new ItemEntity(
                    serverLevel,
                    other.getX(),
                    other.getY(),
                    other.getZ(),
                    new ItemStack(Valentine.Items.LOVE.get())
            );
            serverLevel.addFreshEntity(love);
            serverPlayer.connection.send(new ClientboundSoundPacket(
                    Holder.direct(SoundEvents.EXPERIENCE_ORB_PICKUP),
                    SoundSource.PLAYERS,
                    other.getX(),
                    other.getY(),
                    other.getZ(),
                    1f,
                    1f,
                    serverLevel.getRandom().nextLong()
            ));
            return InteractionResult.CONSUME;
        }

        return InteractionResult.PASS;
    }

    public static void patVFX(ServerPlayer player) {
        ServerLevel level = (ServerLevel) player.level();
        level.sendParticles(
                ParticleTypes.HEART,
                player.getX(),
                player.getY() + 2,
                player.getZ(),
                20,
                0.5,
                0.2,
                0.5,
                0.8d
        );
    }
}
