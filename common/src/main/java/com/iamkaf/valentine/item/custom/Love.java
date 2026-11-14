package com.iamkaf.valentine.item.custom;

import com.iamkaf.valentine.Valentine;
import net.minecraft.core.Holder;
import net.minecraft.core.Position;
//? if >=1.20.2 {
import net.minecraft.core.dispenser.BlockSource;
//?} else {
/*import net.minecraft.core.BlockSource;
*///?}
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.game.ClientboundSoundPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
//? if <1.21.2
/*import net.minecraft.world.InteractionResultHolder;*/
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DispenserBlock;
import org.jetbrains.annotations.NotNull;

import static com.iamkaf.valentine.events.OnPat.patVFX;

public class Love extends Item {
    public Love(Properties properties) {
        super(properties);
    }

    public static void registerDispenseBehavior() {
        DispenserBlock.registerBehavior(Valentine.Items.LOVE.get(), new DefaultDispenseItemBehavior() {
            @Override
            protected ItemStack execute(BlockSource source, ItemStack stack) {
                Position position = DispenserBlock.getDispensePosition(source);
                //? if >=1.20.2 {
                source.level().sendParticles(
                //?} else {
                /*source.getLevel().sendParticles(
                *///?}
                        ParticleTypes.HEART,
                        position.x(),
                        position.y(),
                        position.z(),
                        20,
                        0.5,
                        0.2,
                        0.5,
                        0.8d
                );
                stack.shrink(1);
                return stack;
            }
        });
    }

    //? if >=1.21.2 {
    @Override
    public @NotNull InteractionResult use(Level level, Player player,
            InteractionHand usedHand) {
        if (player.level().isClientSide()) {
            return super.use(level, player, usedHand);
        }
        consume(level, player, usedHand);
        return InteractionResult.CONSUME;
    }
    //?} else {
    /*@Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        if (player.level().isClientSide()) {
            return super.use(level, player, usedHand);
        }
        consume(level, player, usedHand);
        return InteractionResultHolder.consume(player.getItemInHand(usedHand));
    }
    *///?}

    private static void consume(Level level, Player player, InteractionHand usedHand) {
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
    }
}
