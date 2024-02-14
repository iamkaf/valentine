package com.codekaffe.valentine.block.entity;

import com.codekaffe.valentine.KafValentine;
import com.codekaffe.valentine.block.ModBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntities {
    public static void registerBlockEntities() {
        KafValentine.LOGGER.info("Registering Block Entities for " + KafValentine.MOD_ID);
    }    public static final BlockEntityType<LoveyDoveyInfuserBlockEntity> LOVEY_DOVEY_INFUSER_BLOCK_ENTITY = Registry.register(
            Registries.BLOCK_ENTITY_TYPE,
            new Identifier(KafValentine.MOD_ID, "lovey_dovey_infuser_be"),
            FabricBlockEntityTypeBuilder
                    .create(LoveyDoveyInfuserBlockEntity::new, ModBlocks.LOVEY_DOVEY_INFUSER)
                    .build()
    );


}