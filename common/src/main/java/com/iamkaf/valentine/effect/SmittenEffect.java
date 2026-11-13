package com.iamkaf.valentine.effect;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class SmittenEffect extends MobEffect {
    public SmittenEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    //? if >=1.21.2 {
    @Override
    public boolean applyEffectTick(ServerLevel level, LivingEntity livingEntity, int amplifier) {
        hearts(level, livingEntity);
        return super.applyEffectTick(level, livingEntity, amplifier);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }
    //?} else {
    /*@Override
    public void applyEffectTick(LivingEntity livingEntity, int amplifier) {
        if (livingEntity.level() instanceof ServerLevel serverLevel) {
            hearts(serverLevel, livingEntity);
        }
        super.applyEffectTick(livingEntity, amplifier);
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }
    *///?}

    private static void hearts(ServerLevel level, LivingEntity livingEntity) {
        level.sendParticles(
                ParticleTypes.HEART,
                livingEntity.getX(),
                livingEntity.getY() + 2,
                livingEntity.getZ(),
                1,
                0.5,
                0.2,
                0.5,
                0.8d
        );
    }
}
