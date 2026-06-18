package com.iamkaf.valentine.neoforge;

import com.iamkaf.valentine.Valentine;
import com.iamkaf.valentine.item.CustomItemProperties;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.RegisterRangeSelectItemModelPropertyEvent;

@Mod(value = Valentine.MOD_ID, dist = Dist.CLIENT)
public class ValentineNeoForgeClient {
    public ValentineNeoForgeClient(IEventBus eBussy) {
        eBussy.addListener(this::onInit);
        eBussy.addListener(this::onRegisterRangeSelectItemModelPropertyEvent);
    }

    @SuppressWarnings("deprecation")
    public void onInit(FMLClientSetupEvent event) {
        CustomItemProperties.init();
    }

    public void onRegisterRangeSelectItemModelPropertyEvent(RegisterRangeSelectItemModelPropertyEvent event) {
        event.register(CustomItemProperties.CottonCandyColor.ID, CustomItemProperties.CottonCandyColor.MAP_CODEC);
    }
}
