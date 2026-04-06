package com.iamkaf.valentine.fabric.client;

import com.iamkaf.valentine.Valentine;
import com.iamkaf.valentine.item.CustomItemProperties;
import net.fabricmc.api.ClientModInitializer;

public final class ValentineFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        CustomItemProperties.init();
    }
}
