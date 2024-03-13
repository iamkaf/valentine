package com.codekaffe.valentine.item.custom;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ExplosiveCookie extends Item {

    public static final FoodComponent FOOD_COMPONENT = new FoodComponent.Builder()
            .hunger(1)
            .saturationModifier(0.1f)
            .alwaysEdible()
            .build();

    public ExplosiveCookie(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(
            ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context
    ) {
        tooltip.add(Text.literal("That ain't no cookie!!").formatted(Formatting.RED));
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        // gg
        if (!world.isClient()) {
            world.createExplosion(
                    null,
                    user.getX(),
                    user.getY(),
                    user.getZ(),
                    10f,
                    false,
                    World.ExplosionSourceType.BLOCK
            );
        }

        return super.finishUsing(stack, world, user);
    }
}
