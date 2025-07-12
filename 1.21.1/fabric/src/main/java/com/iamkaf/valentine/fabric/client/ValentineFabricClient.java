package com.iamkaf.valentine.fabric.client;

import com.iamkaf.valentine.Valentine;
import com.iamkaf.valentine.item.CustomItemProperties;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;

public final class ValentineFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // NOTE: In NeoForge, these are defined as a "render_type" property of "minecraft:cutout" in the model json.
        BlockRenderLayerMap.putBlock(Valentine.Blocks.COTTON_CANDY_CROP.get(), ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(Valentine.Blocks.ARISTEA.get(), ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(Valentine.Blocks.POTTED_ARISTEA.get(), ChunkSectionLayer.CUTOUT);

        CustomItemProperties.init();
    }
}
