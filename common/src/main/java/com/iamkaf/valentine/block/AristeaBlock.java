package com.iamkaf.valentine.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BonemealableBlock;
//? if >=26.3
/*import net.minecraft.world.level.block.BonemealSource;*/
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.state.BlockState;

public class AristeaBlock extends FlowerBlock implements BonemealableBlock {
    public AristeaBlock(Holder<MobEffect> suspiciousStewEffect, int effectDuration, Properties settings) {
        super(suspiciousStewEffect, effectDuration, settings);
    }

    @Override
    //? if >=26.3
    /*public boolean isValidBonemealTarget(LevelReader world, BlockPos pos, BlockState state, BonemealSource source) {*/
    //? if <26.3
    public boolean isValidBonemealTarget(LevelReader world, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    //? if >=26.3
    /*public boolean isBonemealSuccess(Level world, RandomSource random, BlockPos pos, BlockState state, BonemealSource source) {*/
    //? if <26.3
    public boolean isBonemealSuccess(Level world, RandomSource random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    //? if >=26.3
    /*public void performBonemeal(ServerLevel world, RandomSource random, BlockPos pos, BlockState state, BonemealSource source) {*/
    //? if <26.3
    public void performBonemeal(ServerLevel world, RandomSource random, BlockPos pos, BlockState state) {
        AristeaBlock.popResource(world, pos, new ItemStack(this));
    }
}
