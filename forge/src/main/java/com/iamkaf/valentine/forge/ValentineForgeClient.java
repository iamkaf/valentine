package com.iamkaf.valentine.forge;

import com.iamkaf.valentine.item.CustomItemProperties;

public final class ValentineForgeClient {
    private ValentineForgeClient() {
    }

    public static void init() {
        CustomItemProperties.init();
    }
}
