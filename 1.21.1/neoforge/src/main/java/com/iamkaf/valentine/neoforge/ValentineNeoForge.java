package com.iamkaf.valentine.neoforge;

import com.iamkaf.valentine.Valentine;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(Valentine.MOD_ID)
public final class ValentineNeoForge {
    public ValentineNeoForge(IEventBus eBussy) {
        // Run our common setup.
        Valentine.init();
        RegisterImpl.ITEMS.register(eBussy);
        RegisterImpl.BLOCKS.register(eBussy);
        RegisterImpl.TABS.register(eBussy);
        RegisterImpl.DATA_COMPONENT_TYPES.register(eBussy);
        RegisterImpl.ARMOR_MATERIAL.register(eBussy);
        RegisterImpl.MOB_EFFECT.register(eBussy);
        RegisterImpl.POTION.register(eBussy);
    }


}
