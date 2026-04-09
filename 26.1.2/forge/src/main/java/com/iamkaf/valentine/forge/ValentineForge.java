package com.iamkaf.valentine.forge;

import com.iamkaf.valentine.Valentine;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.loading.FMLLoader;

@Mod(Valentine.MOD_ID)
public final class ValentineForge {
    public ValentineForge() {
        Valentine.init();
        if (FMLLoader.getDist().isClient()) {
            ValentineForgeClient.init();
        }
        ValentineForgeEvents.init();
    }
}
