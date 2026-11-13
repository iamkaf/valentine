import com.iamkaf.multiloader.support.VersionPolicy
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension

plugins {
    id("com.iamkaf.multiloader.forge")
}

val catalog = mcCatalog()
val patchouli = catalog.findLibrary("patchouli-forge")

fun mcCatalog(): VersionCatalog {
    val catalogs = extensions.getByType<VersionCatalogsExtension>()
    val name = "libsMc${project.name.replace(".", "").replace("-", "")}"
    return catalogs.named(name)
}

dependencies {
    if (patchouli.isPresent) {
        add(if (VersionPolicy.usesLegacyForgePlugin(project.name)) "modRuntimeOnly" else "runtimeOnly", patchouli.get())
    }
}
