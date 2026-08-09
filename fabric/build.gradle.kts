plugins {
    id("com.iamkaf.multiloader.fabric")
}

val minecraftVersion = project.name

dependencies {
    if (minecraftVersion in setOf("26.1", "26.1.1", "26.1.2")) {
        runtimeOnly("maven.modrinth:patchouli:AveV4Tjn")
    }
}
