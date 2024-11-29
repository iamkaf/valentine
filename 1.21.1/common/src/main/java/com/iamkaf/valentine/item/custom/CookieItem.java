package com.iamkaf.valentine.item.custom;

import com.iamkaf.valentine.events.OnCookieEaten;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Supplier;

public class CookieItem extends Item {
    private final String id;
    private final ChatFormatting tooltipColor;
    private Supplier<? extends Item> specialCookie = () -> null;

    public CookieItem(Properties properties, String id, ChatFormatting tooltipColor) {
        super(properties);
        this.id = id;
        this.tooltipColor = tooltipColor;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents,
            TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.translatable("item.kafvalentine." + id + ".tooltip")
                .withStyle(tooltipColor));
    }

    @Override
    public @NotNull ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {
        if (livingEntity instanceof Player player) {
            OnCookieEaten.execute(id, stack, player, level);
        }

        return super.finishUsingItem(stack, level, livingEntity);
    }

    public void setUpgrade(Supplier<? extends Item> upgrade) {
        this.specialCookie = upgrade;
    }

    public @Nullable Item upgrade() {
        return this.specialCookie.get();
    }
}
