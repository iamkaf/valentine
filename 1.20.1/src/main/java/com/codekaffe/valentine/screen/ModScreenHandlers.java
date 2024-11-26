package com.codekaffe.valentine.screen;

import com.codekaffe.valentine.KafValentine;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class ModScreenHandlers {
    public static void registerScreenHandlers() {
        KafValentine.LOGGER.info("Registering Screen Handlers for " + KafValentine.MOD_ID);
    }

    public static final ScreenHandlerType<LoveyDoveyInfusingScreenHandler> LOVEY_LOVEY_SCREEN_HANDLER = Registry.register(
            Registries.SCREEN_HANDLER,
            new Identifier(KafValentine.MOD_ID, "lovey_dovey_infusing"),
            new ExtendedScreenHandlerType<>(LoveyDoveyInfusingScreenHandler::new)
    );


}