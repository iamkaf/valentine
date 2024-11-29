package com.iamkaf.valentine.item.custom;

import com.iamkaf.amber.api.item.SmartTooltip;
import com.iamkaf.valentine.Valentine;
import com.iamkaf.valentine.item.ItemColorDataComponent;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CottonCandyItem extends Item {
    public CottonCandyItem(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        Player player = context.getPlayer();

        if (level.isClientSide || player == null) {
            return super.useOn(context);
        }

        BlockPos pos = context.getClickedPos();
        if (level.getBlockState(pos).is(Blocks.HONEYCOMB_BLOCK)) {
            ItemStack colorfulCottonCandy = new ItemStack(Valentine.Items.COLORFUL_COTTON_CANDY.get());
            colorfulCottonCandy.set(Valentine.DataComponents.COLOR.get(),
                    new ItemColorDataComponent(level.getRandom().nextInt(3) + 1)
            );
//            player.drop(colorfulCottonCandy, true, false);
            level.addFreshEntity(new ItemEntity(level,
                    pos.getX() + 0.5f,
                    pos.getY() + 1,
                    pos.getZ() + 0.5f,
                    colorfulCottonCandy
            ));
            context.getItemInHand().shrink(1);
            level.playSound(null, pos, SoundEvents.HONEY_BLOCK_PLACE, SoundSource.BLOCKS, 1f, 1f);

            if (level.getRandom().nextFloat() < 0.25) {
                level.destroyBlock(pos, false, player);
            }
            return InteractionResult.SUCCESS;
        }

        return super.useOn(context);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents,
            TooltipFlag tooltipFlag) {
        SmartTooltip smartTooltip = new SmartTooltip();
        smartTooltip.add(Component.translatable("text.kafvalentine.hold_shift")
                .withStyle(ChatFormatting.AQUA));
        smartTooltip.shift(Component.translatable("item.kafvalentine.cotton_candy.tooltip")
                .withStyle(ChatFormatting.AQUA));
        smartTooltip.into(tooltipComponents);
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}
