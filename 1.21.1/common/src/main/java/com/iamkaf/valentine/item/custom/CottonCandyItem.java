package com.iamkaf.valentine.item.custom;

import com.iamkaf.valentine.Valentine;
import com.iamkaf.valentine.item.ItemColorDataComponent;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;

import java.util.List;

public class CottonCandyItem extends Item {
    public CottonCandyItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        Player player = context.getPlayer();

        if (level.isClientSide || player == null) {
            return super.useOn(context);
        }

        if (level.getBlockState(context.getClickedPos()).is(Blocks.HONEYCOMB_BLOCK)) {
            ItemStack colorfulCottonCandy = new ItemStack(Valentine.Items.COLORFUL_COTTON_CANDY.get());
            colorfulCottonCandy.set(Valentine.DataComponents.COLOR.get(),
                    new ItemColorDataComponent(level.getRandom().nextInt(3) + 1)
            );
            player.drop(colorfulCottonCandy, true, false);
            context.getItemInHand().shrink(1);
            level.playSound(
                    null,
                    context.getClickedPos(),
                    SoundEvents.HONEY_BLOCK_PLACE,
                    SoundSource.BLOCKS,
                    1f,
                    1f
            );

            if (level.getRandom().nextFloat() < 0.25) {
                level.destroyBlock(context.getClickedPos(), false, player);
            }
            return InteractionResult.SUCCESS;
        }

        return super.useOn(context);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents,
            TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.translatable("item.kafvalentine.cotton_candy.tooltip")
                .withStyle(ChatFormatting.AQUA));
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}
