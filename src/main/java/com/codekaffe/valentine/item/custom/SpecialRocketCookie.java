package com.codekaffe.valentine.item.custom;

import com.google.common.collect.Lists;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.FireworkRocketEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.text.Text;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Formatting;
import net.minecraft.util.Util;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SpecialRocketCookie extends Item {

    public static final FoodComponent FOOD_COMPONENT = new FoodComponent.Builder()
            .hunger(4)
            .saturationModifier(0.2f)
            .alwaysEdible()
            .snack()
            .build();

    public SpecialRocketCookie(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(
            ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context
    ) {
        tooltip.add(Text
                .literal("Do you want to watch the fireworks together?")
                .formatted(Formatting.GOLD));
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (!world.isClient()) {
            Random random = user.getRandom();
            DyeColor dyeColor = Util.getRandom(DyeColor.values(), random);
            DyeColor dyeColor2 = Util.getRandom(DyeColor.values(), random);
            ItemStack itemStack = this.createFirework(dyeColor, dyeColor2, 0);
            FireworkRocketEntity fireworkRocketEntity = new FireworkRocketEntity(world,
                    user,
                    user.getX(),
                    user.getEyeY(),
                    user.getZ(),
                    itemStack
            );
            world.spawnEntity(fireworkRocketEntity);
        }
        return super.finishUsing(stack, world, user);
    }

    private ItemStack createFirework(DyeColor color, DyeColor color2, int flight) {
        ItemStack itemStack = new ItemStack(Items.FIREWORK_ROCKET, 1);
        ItemStack itemStack2 = new ItemStack(Items.FIREWORK_STAR);
        NbtCompound nbtCompound = itemStack2.getOrCreateSubNbt("Explosion");
        ArrayList<Integer> list = Lists.newArrayList();
        list.add(color.getFireworkColor());
        list.add(color2.getFireworkColor());
        nbtCompound.putIntArray("Colors", list);
        // consider changing this to large ball
        nbtCompound.putByte("Type", (byte) FireworkRocketItem.Type.LARGE_BALL.getId());
        NbtCompound nbtCompound2 = itemStack.getOrCreateSubNbt("Fireworks");
        NbtList nbtList = new NbtList();
        NbtCompound nbtCompound3 = itemStack2.getSubNbt("Explosion");
        if (nbtCompound3 != null) {
            nbtList.add(nbtCompound3);
        }
        nbtCompound2.putByte("Flight", (byte) flight);
        if (!nbtList.isEmpty()) {
            nbtCompound2.put("Explosions", nbtList);
        }
        return itemStack;
    }
}
