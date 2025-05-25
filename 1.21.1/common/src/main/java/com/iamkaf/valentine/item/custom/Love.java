package com.iamkaf.valentine.item.custom;

import net.minecraft.core.Holder;
import net.minecraft.network.protocol.game.ClientboundSoundPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import static com.iamkaf.valentine.events.OnPat.patVFX;

public class Love extends Item {
    public Love(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull InteractionResult use(Level level, Player player,
            InteractionHand usedHand) {
        if (player.level().isClientSide) {
            return super.use(level, player, usedHand);
        }
        ServerPlayer serverPlayer = (ServerPlayer) player;
        patVFX(serverPlayer);
        ItemStack stack = serverPlayer.getItemInHand(usedHand);
        stack.shrink(1);
        serverPlayer.connection.send(new ClientboundSoundPacket(
                Holder.direct(SoundEvents.ITEM_PICKUP),
                SoundSource.PLAYERS,
                player.getX(),
                player.getY(),
                player.getZ(),
                1f,
                1f,
                level.getRandom().nextLong()
        ));
        return InteractionResult.CONSUME;
    }
}
