package com.iamkaf.valentine.item.custom;

import com.iamkaf.amber.api.functions.v1.ClientFunctions.SmartTooltip;
import com.iamkaf.valentine.Valentine;
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
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

@SuppressWarnings("deprecation") // I'll deal with the tooltip deprecation when it's time.
public class CottonCandyItem extends Item {
    public CottonCandyItem(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        Player player = context.getPlayer();

        if (level.isClientSide() || player == null) {
            return super.useOn(context);
        }

        BlockPos pos = context.getClickedPos();
        if (level.getBlockState(pos).is(Blocks.HONEYCOMB_BLOCK) || level.getBlockState(pos).is(Blocks.HONEY_BLOCK)) {
            ItemStack colorfulCottonCandy = new ItemStack(Valentine.Items.CANDIED_COTTON_CANDY_CANDY.get());
//            colorfulCottonCandy.set(Valentine.DataComponents.COLOR.get(),
//                    new ItemColorDataComponent(level.getRandom().nextInt(3) + 1)
//            );
            level.addFreshEntity(new ItemEntity(
                    level,
                    pos.getX() + 0.5f,
                    pos.getY() + 1,
                    pos.getZ() + 0.5f,
                    colorfulCottonCandy
            ));
            context.getItemInHand().shrink(1);
            level.playSound(null, pos, SoundEvents.HONEY_BLOCK_PLACE, SoundSource.BLOCKS, 1f, 1f);

            if (level.getRandom().nextFloat() < 0.15) {
                level.destroyBlock(pos, false, player);
            }
            return InteractionResult.SUCCESS;
        }

        return super.useOn(context);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay tooltipDisplay,
            Consumer<Component> tooltipAdder, TooltipFlag flag) {
        super.appendHoverText(stack, context, tooltipDisplay, tooltipAdder, flag);
        new SmartTooltip()
                .add(Component.translatable("text.kafvalentine.hold_shift").withStyle(ChatFormatting.AQUA))
                .shift(Component.translatable("item.kafvalentine.cotton_candy.tooltip").withStyle(ChatFormatting.AQUA))
                .into(tooltipAdder);
    }
}
