package com.iamkaf.valentine.forge;

import com.iamkaf.valentine.Valentine;
import com.iamkaf.valentine.item.custom.Love;
import net.minecraft.core.registries.Registries;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.loading.FMLLoader;
import net.minecraftforge.registries.RegisterEvent;

@Mod(Valentine.MOD_ID)
public final class ValentineForge {
    public ValentineForge(FMLJavaModLoadingContext context) {
        Valentine.init();
        RegisterEvent.getBus(context.getModBusGroup()).addListener(event -> {
            if (event.getRegistryKey().equals(Registries.ITEM)) {
                Love.registerDispenseBehavior();
            }
        });
        if (FMLLoader.getDist().isClient()) {
            ValentineForgeClient.init();
        }
        ValentineForgeEvents.init();
    }
}
