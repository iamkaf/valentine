package com.codekaffe.valentine.block.custom;

import net.minecraft.block.BlockState;
import net.minecraft.block.Fertilizable;
import net.minecraft.block.FlowerBlock;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

public class AristeaBlock extends FlowerBlock implements Fertilizable {
    public AristeaBlock(
            StatusEffect suspiciousStewEffect, int effectDuration, Settings settings
    ) {
        super(suspiciousStewEffect, effectDuration, settings);
    }

    @Override
    public boolean isFertilizable(
            WorldView world,
            BlockPos pos,
            BlockState state,
            boolean isClient
    ) {
        return true;
    }

    @Override
    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        AristeaBlock.dropStack(world, pos, new ItemStack(this));
    }
}
