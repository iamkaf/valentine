import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import com.iamkaf.multiloader.support.MultiloaderProjectContext

plugins {
    id("com.iamkaf.multiloader.fabric")
}

extensions.configure<com.iamkaf.multiloader.fabric.MultiloaderFabricExtension>("multiloaderFabric") {
    commonDatagen.set(project.name == "26.3")
}

val catalog = mcCatalog()
val patchouli = catalog.findLibrary("patchouli-fabric")

fun mcCatalog(): VersionCatalog {
    val catalogs = extensions.getByType<VersionCatalogsExtension>()
    val name = "libsMc${project.name.replace(".", "").replace("-", "")}"
    return catalogs.named(name)
}

dependencies {
    if (patchouli.isPresent) {
        if (MultiloaderProjectContext.of(project).useUnobfuscatedMinecraft()) {
            runtimeOnly(patchouli.get())
        } else {
            // Loom has to remap the Modrinth jar, and remapping drops the Fiber jar nested inside it.
            "modRuntimeOnly"(patchouli.get())
            "modRuntimeOnly"("me.zeroeightsix:fiber:0.23.0-2")
        }
    }
}
