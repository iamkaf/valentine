plugins {
    id("dev.kikugie.stonecutter")
    id("fabric-loom") apply false
    id("net.fabricmc.fabric-loom") apply false
    id("com.iamkaf.multiloader.root")
    id("com.iamkaf.teakit") version "0.13.2"
}

teakit {
    runnerVersion.set("0.13.2")
    testDirectories.add("test/teakit")
    timeoutSeconds.set(720)
    failOnRuntimeIncomplete.set(true)
    background.set(true)
}

stonecutter active "26.1.2".let { multiloaderStonecutter.active(it) }

val patchouliRuntimeByLoader = mapOf(
    "fabric" to "maven.modrinth:patchouli:AveV4Tjn",
    "neoforge" to "maven.modrinth:patchouli:2CsnFLom",
)
val patchouliMinecraftVersions = setOf("26.1", "26.1.1", "26.1.2")

subprojects {
    val loader = parent?.name ?: return@subprojects
    val coordinate = patchouliRuntimeByLoader[loader] ?: return@subprojects
    if (name !in patchouliMinecraftVersions) return@subprojects

    pluginManager.withPlugin("java") {
        dependencies.add("runtimeOnly", coordinate)
    }
}

stonecutter parameters {
    replacements.string(eval(current.version, ">=1.21.11")) {
        replace("ResourceLocation", "Identifier")
    }
}
