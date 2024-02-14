package com.codekaffe.valentine;

import com.codekaffe.valentine.block.ModBlocks;
import com.codekaffe.valentine.screen.LoveyDoveyInfusingScreen;
import com.codekaffe.valentine.screen.ModScreenHandlers;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.RenderLayer;

public class KafValentineClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.COTTON_CANDY_CROP, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.ARISTEA, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_ARISTEA, RenderLayer.getCutout());

        HandledScreens.register(ModScreenHandlers.LOVEY_LOVEY_SCREEN_HANDLER,
                LoveyDoveyInfusingScreen::new
        );
    }
}
