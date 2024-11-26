package com.codekaffe.valentine.sound;

import com.codekaffe.valentine.KafValentine;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSounds {
    public static final SoundEvent UWU = registerSoundEvent("uwu");

    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = new Identifier(KafValentine.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    public static void registerSounds() {
        KafValentine.LOGGER.info("Registering sounds for " + KafValentine.MOD_ID);
    }
}
