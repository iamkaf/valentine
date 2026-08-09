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

stonecutter parameters {
    replacements.string(eval(current.version, ">=1.21.11")) {
        replace("ResourceLocation", "Identifier")
    }
}
