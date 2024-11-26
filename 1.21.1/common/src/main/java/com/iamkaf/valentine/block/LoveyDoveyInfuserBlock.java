package com.iamkaf.valentine.block;

import com.iamkaf.valentine.item.custom.CookieItem;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;

public class LoveyDoveyInfuserBlock extends Block {
    public LoveyDoveyInfuserBlock(Properties properties) {
        super(properties);
    }

    private static void feedback(Level level, BlockPos position) {
        level.playSound(null, position, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS);
        if (level instanceof ServerLevel serverLevel) {
            serverLevel.sendParticles(ParticleTypes.HEART,
                    position.getX() + 0.5f,
                    position.getY() + 0.5f,
                    position.getZ() + 0.5f,
                    8,
                    0.5,
                    0.2,
                    0.5,
                    0.8d
            );
        }
    }

    @Override
    protected @NotNull ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level,
            BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (stack.getItem() instanceof CookieItem cookie) {
            Item upgrade = cookie.upgrade();
            if (upgrade != null) {
                stack.shrink(1);
                ItemStack upgradeStack = new ItemStack(upgrade);
                boolean added = player.addItem(upgradeStack);
                if (!added) {
                    level.addFreshEntity(new ItemEntity(level,
                            pos.getX() + 0.5f,
                            pos.getY() + 1,
                            pos.getZ() + 0.5f,
                            upgradeStack
                    ));
                }
                feedback(level, pos.above());
                return ItemInteractionResult.sidedSuccess(level.isClientSide);
            }
        }

        return super.useItemOn(stack, state, level, pos, player, hand, hitResult);
    }
}
