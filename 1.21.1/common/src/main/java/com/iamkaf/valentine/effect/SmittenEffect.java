package com.iamkaf.valentine.effect;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.DiggerItem;

public class SmittenEffect extends MobEffect {
    public SmittenEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyEffectTick(LivingEntity livingEntity, int amplifier) {
        if (livingEntity.level() instanceof ServerLevel serverLevel) {
            serverLevel.sendParticles(
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
        return super.applyEffectTick(livingEntity, amplifier);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }
}
