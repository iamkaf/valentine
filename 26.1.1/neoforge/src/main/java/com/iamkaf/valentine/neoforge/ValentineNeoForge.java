package com.iamkaf.valentine.neoforge;

import com.iamkaf.valentine.Valentine;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(Valentine.MOD_ID)
public final class ValentineNeoForge {
    public ValentineNeoForge(IEventBus eBussy) {
        Valentine.init();
    }
}
