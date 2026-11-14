plugins {
    id("dev.kikugie.stonecutter")
    id("fabric-loom") apply false
    id("net.fabricmc.fabric-loom") apply false
    id("com.iamkaf.multiloader.root")
    id("com.iamkaf.teakit") version "0.19.0"
}

teakit {
    runnerVersion.set("0.18.0")
    testDirectories.add("test/teakit")
    timeoutSeconds.set(720)
    failOnRuntimeIncomplete.set(true)
    background.set(true)
}

stonecutter active "26.1.2".let { multiloaderStonecutter.active(it) }

stonecutter parameters {
    replacements.string(eval(current.version, ">=1.21.11")) {
        replace("ResourceLocation", "Identifier")
        replace(".location()", ".identifier()")
        replace("net.minecraft.world.entity.animal.Fox;", "net.minecraft.world.entity.animal.fox.Fox;")
        replace("import net.minecraft.Util;", "import net.minecraft.util.Util;")
    }
    replacements.regex(eval(current.version, ">=1.21.10")) {
        replace("\\.noCollission\\(" to ".noCollision(", "\\.noCollision\\(" to ".noCollission(")
    }
    replacements.regex(eval(current.version, ">=1.21.5")) {
        replace("MobEffects\\.MOVEMENT_SPEED\\b" to "MobEffects.SPEED", "MobEffects\\.SPEED\\b" to "MobEffects.MOVEMENT_SPEED")
        replace("MobEffects\\.DIG_SPEED\\b" to "MobEffects.HASTE", "MobEffects\\.HASTE\\b" to "MobEffects.DIG_SPEED")
        replace("MobEffects\\.HEAL\\b" to "MobEffects.INSTANT_HEALTH", "MobEffects\\.INSTANT_HEALTH\\b" to "MobEffects.HEAL")
        replace("MobEffects\\.JUMP\\b" to "MobEffects.JUMP_BOOST", "MobEffects\\.JUMP_BOOST\\b" to "MobEffects.JUMP")
        replace("MobEffects\\.DAMAGE_RESISTANCE\\b" to "MobEffects.RESISTANCE", "MobEffects\\.RESISTANCE\\b" to "MobEffects.DAMAGE_RESISTANCE")
    }
    replacements.regex(eval(current.version, ">=1.20.5")) {
        replace("\\.saturationMod\\(" to ".saturationModifier(", "\\.saturationModifier\\(" to ".saturationMod(")
        replace("\\.alwaysEat\\(" to ".alwaysEdible(", "\\.alwaysEdible\\(" to ".alwaysEat(")
    }
    replacements.regex(eval(current.version, ">=1.20.3")) {
        replace("Properties\\.copy\\(" to "Properties.ofFullCopy(", "Properties\\.ofFullCopy\\(" to "Properties.copy(")
        replace("Blocks\\.GRASS\\b" to "Blocks.SHORT_GRASS", "Blocks\\.SHORT_GRASS\\b" to "Blocks.GRASS")
    }
}
