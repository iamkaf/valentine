package com.iamkaf.valentine.fabric;

import com.iamkaf.valentine.Valentine;
import com.iamkaf.valentine.Register;
import com.iamkaf.valentine.block.CottonCandyCropBlock;
import com.iamkaf.valentine.item.custom.Love;
import com.iamkaf.valentine.worldgen.WorldGen;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
//? if >=26.3 {
/*import com.iamkaf.amber.api.event.v1.events.common.ItemEvents;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.component.Compostable;
import net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProviders;
*///?} else {
import net.fabricmc.fabric.api.registry.CompostableRegistry;
import net.fabricmc.fabric.api.registry.FabricPotionBrewingBuilder;
//?}
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.alchemy.Potions;

public final class ValentineFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        Valentine.init();
        Love.registerDispenseBehavior();
        //? if <26.3
        Registry.register(BuiltInRegistries.BLOCK_TYPE, Valentine.resource("cotton_candy_crop"), CottonCandyCropBlock.CODEC);
        //? if <26.3
        registerPotionRecipes();
        registerCompostables();
        registerWorldgen();
    }

    //? if <26.3 {
    private void registerPotionRecipes() {
        FabricPotionBrewingBuilder.BUILD.register(builder -> {
            builder.registerPotionRecipe(
                    Potions.AWKWARD,
                    net.minecraft.world.item.crafting.Ingredient.of(Valentine.Blocks.ARISTEA.get().asItem()),
                    BuiltInRegistries.POTION.wrapAsHolder(Valentine.Potions.SMITTEN_POTION.get())
            );
        });
    }

    //?}

    private void registerCompostables() {
        //? if >=26.3 {
        /*ItemEvents.MODIFY_DEFAULT_COMPONENTS.register(context -> Register.compostables().forEach((item, chance) -> {
            var layers = switch (Math.round(chance * 100)) {
                case 30 -> ContextIntProviders.COMPOSTABLE_LOW;
                case 50 -> ContextIntProviders.COMPOSTABLE_LOW_MEDIUM;
                case 65 -> ContextIntProviders.COMPOSTABLE_MEDIUM;
                case 85 -> ContextIntProviders.COMPOSTABLE_MEDIUM_HIGH;
                case 100 -> ContextIntProviders.COMPOSTABLE_ALWAYS_ADD_ONE;
                default -> throw new IllegalArgumentException("Unsupported compost chance: " + chance);
            };
            context.modify(item.get().asItem(), builder -> builder.set(DataComponents.COMPOSTABLE, new Compostable(layers)));
        }));
        *///?} else {
        Register.compostables().forEach((item, chance) -> CompostableRegistry.INSTANCE.add(item.get(), chance));
        //?}
    }

    private void registerWorldgen() {
        for (var biome : WorldGen.ARISTEA_BIOMES) {
            BiomeModifications.addFeature(BiomeSelectors.includeByKey(biome), WorldGen.ARISTEA_STEP, WorldGen.ARISTEA_PLACED_KEY);
        }
    }
}
