package com.iamkaf.valentine.fabric.client;

import com.iamkaf.valentine.Valentine;
import com.iamkaf.valentine.item.CustomItemProperties;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.renderer.RenderType;

public final class ValentineFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // Fabric ignores the models' "render_type", and 1.21.1 renders unmapped blocks solid.
        BlockRenderLayerMap.INSTANCE.putBlocks(
                RenderType.cutout(),
                Valentine.Blocks.ARISTEA.get(),
                Valentine.Blocks.POTTED_ARISTEA.get(),
                Valentine.Blocks.COTTON_CANDY_CROP.get()
        );

        CustomItemProperties.init();
    }
}
