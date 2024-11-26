package com.iamkaf.valentine.fabric.client;

import com.iamkaf.valentine.Valentine;
import com.iamkaf.valentine.item.CustomItemProperties;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.renderer.RenderType;

public final class ValentineFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // NOTE: In NeoForge, these are defined as a "render_type" property of "minecraft:cutout" in the model json.
        BlockRenderLayerMap.INSTANCE.putBlock(Valentine.Blocks.COTTON_CANDY_CROP.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(Valentine.Blocks.ARISTEA.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(Valentine.Blocks.POTTED_ARISTEA.get(), RenderType.cutout());

        CustomItemProperties.init();
    }
}
