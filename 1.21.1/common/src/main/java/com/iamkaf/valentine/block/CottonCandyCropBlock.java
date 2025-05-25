package com.iamkaf.valentine.block;

import com.iamkaf.valentine.Valentine;
import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import org.jetbrains.annotations.NotNull;

public class CottonCandyCropBlock extends CropBlock {
    public static final int MAX_AGE = 5;
    public static final MapCodec<CropBlock> CODEC = simpleCodec(CottonCandyCropBlock::new);

    public CottonCandyCropBlock(Properties settings) {
        super(settings);
    }

    @Override
    public @NotNull MapCodec<? extends CropBlock> codec() {
        return CODEC;
    }

    @Override
    protected @NotNull ItemLike getBaseSeedId() {
        return Valentine.Items.COTTON_CANDY_SEEDS.get();
    }

    @Override
    public int getMaxAge() {
        return MAX_AGE;
    }
}
