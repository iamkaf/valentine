package com.iamkaf.valentine.fabric.client;

import com.iamkaf.valentine.Valentine;
import com.iamkaf.valentine.item.CustomItemProperties;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;

public final class ValentineFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // Fabric ignores the models' "render_type", and 1.21.11 renders unmapped blocks solid.
        BlockRenderLayerMap.putBlocks(
                ChunkSectionLayer.CUTOUT,
                Valentine.Blocks.ARISTEA.get(),
                Valentine.Blocks.POTTED_ARISTEA.get(),
                Valentine.Blocks.COTTON_CANDY_CROP.get()
        );

        CustomItemProperties.init();
    }
}
