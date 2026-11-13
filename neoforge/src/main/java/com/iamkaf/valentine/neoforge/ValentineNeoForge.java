package com.iamkaf.valentine.neoforge;

import com.iamkaf.valentine.Register;
import com.iamkaf.valentine.Valentine;
import com.iamkaf.valentine.item.custom.Love;
import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.RegisterEvent;

@Mod(Valentine.MOD_ID)
public final class ValentineNeoForge {
    public ValentineNeoForge(IEventBus eBussy) {
        Valentine.init();
        // NeoForge's compostables data map covers older versions. 26.3 replaced it with an item component.
        //? if >=26.3
        /*Register.registerCompostables();*/
        eBussy.addListener((RegisterEvent event) -> {
            if (event.getRegistryKey().equals(Registries.ITEM)) {
                Love.registerDispenseBehavior();
            }
        });
    }
}
