package com.iamkaf.valentine.neoforge;

import com.iamkaf.valentine.Valentine;
import com.iamkaf.valentine.item.CustomItemProperties;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.RegisterRangeSelectItemModelPropertyEvent;

@Mod(value = Valentine.MOD_ID, dist = Dist.CLIENT)
public class ValentineNeoForgeClient {
    public ValentineNeoForgeClient(IEventBus eBussy) {
        eBussy.addListener(this::onInit);
        eBussy.addListener(this::onRegisterRangeSelectItemModelPropertyEvent);
    }

    @SuppressWarnings("deprecation")
    public void onInit(FMLClientSetupEvent event) {
        // This has been deprecated for 3 years. I'll take my chances.
        // The render layer is set here instead of the model json because datagen is done on the fabric side.
        // If this ever breaks I'll go ahead and do it the "right" way.
        ItemBlockRenderTypes.setRenderLayer(Valentine.Blocks.COTTON_CANDY_CROP.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(Valentine.Blocks.ARISTEA.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(Valentine.Blocks.POTTED_ARISTEA.get(), RenderType.cutout());
        CustomItemProperties.init();
    }

    public void onRegisterRangeSelectItemModelPropertyEvent(RegisterRangeSelectItemModelPropertyEvent event) {
        event.register(CustomItemProperties.CottonCandyColor.ID, CustomItemProperties.CottonCandyColor.MAP_CODEC);
    }
}
