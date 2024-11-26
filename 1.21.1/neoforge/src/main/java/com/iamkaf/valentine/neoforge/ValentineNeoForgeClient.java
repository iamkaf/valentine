package com.iamkaf.valentine.neoforge;

import com.iamkaf.valentine.Valentine;
import com.iamkaf.valentine.item.CustomItemProperties;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;

@Mod(value = Valentine.MOD_ID, dist = Dist.CLIENT)
public class ValentineNeoForgeClient {
    public ValentineNeoForgeClient(IEventBus eBussy) {
        eBussy.addListener(this::onInit);
    }

    public void onInit(FMLClientSetupEvent event) {
        CustomItemProperties.init();
    }
}
