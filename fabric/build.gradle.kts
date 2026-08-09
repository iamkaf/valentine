import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension

plugins {
    id("com.iamkaf.multiloader.fabric")
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
        runtimeOnly(patchouli.get())
    }
}
