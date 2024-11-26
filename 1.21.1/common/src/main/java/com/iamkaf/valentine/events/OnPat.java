package com.iamkaf.valentine.events;

import com.iamkaf.valentine.Valentine;
import dev.architectury.event.EventResult;
import dev.architectury.event.events.common.InteractionEvent;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.game.ClientboundSoundPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class OnPat {
    public static void init() {
        InteractionEvent.INTERACT_ENTITY.register(OnPat::onInteractEntity);
    }

    private static EventResult onInteractEntity(Player player, Entity entity, InteractionHand hand) {
        if (!hand.equals(InteractionHand.MAIN_HAND)) {
            return EventResult.pass();
        }

        if (!player.getItemInHand(hand).isEmpty() || !player.isShiftKeyDown()) {
            return EventResult.pass();
        }

        if (player instanceof ServerPlayer serverPlayer && entity instanceof ServerPlayer other) {
            ServerLevel level = (ServerLevel) serverPlayer.level();
            patVFX(other);
            var love = new ItemEntity(
                    level,
                    other.getX(),
                    other.getY(),
                    other.getZ(),
                    new ItemStack(Valentine.Items.LOVE.get())
            );
            level.addFreshEntity(love);
            serverPlayer.connection.send(new ClientboundSoundPacket(
                    Holder.direct(SoundEvents.EXPERIENCE_ORB_PICKUP),
                    SoundSource.PLAYERS,
                    other.getX(),
                    other.getY(),
                    other.getZ(),
                    1f,
                    1f,
                    level.getRandom().nextLong()
            ));
            return EventResult.interruptTrue();
        }

        return EventResult.pass();
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
