package com.iamkaf.valentine.forge;

import com.iamkaf.valentine.Valentine;
import com.iamkaf.valentine.item.custom.Love;
import net.minecraft.core.registries.Registries;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.loading.FMLLoader;
import net.minecraftforge.registries.RegisterEvent;
//? if <1.21.6
/*import net.minecraftforge.eventbus.api.IEventBus;*/

@Mod(Valentine.MOD_ID)
public final class ValentineForge {
    //? if >=1.21.6 {
    public ValentineForge(FMLJavaModLoadingContext context) {
        Valentine.init();
        RegisterEvent.getBus(context.getModBusGroup()).addListener(ValentineForge::onRegister);
        if (FMLLoader.getDist().isClient()) {
            ValentineForgeClient.init();
        }
        //? if <26.3
        ValentineForgeEvents.init();
    }
    //?} else {
    /*public ValentineForge() {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        Valentine.init();
        modBus.addListener(ValentineForge::onRegister);
        if (FMLLoader.getDist().isClient()) {
            ValentineForgeClient.init();
        }
        ValentineForgeEvents.init(modBus);
    }
    *///?}

    private static void onRegister(RegisterEvent event) {
        if (event.getRegistryKey().equals(Registries.ITEM)) {
            Love.registerDispenseBehavior();
        }
    }
}
