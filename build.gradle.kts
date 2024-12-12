import net.minecrell.pluginyml.bukkit.BukkitPluginDescription
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    kotlin("jvm") version "2.1.0"
    kotlin("plugin.serialization") version "2.1.0"

    id("io.papermc.paperweight.userdev") version "1.7.7"
    id("net.minecrell.plugin-yml.paper") version "0.6.0"
    id("xyz.jpenilla.run-paper") version "2.3.1"

    id("com.gradleup.shadow") version "8.3.5"
}

version = 1
group = "ng.bossi.mini"

repositories {
    mavenLocal {
        content {
            includeGroupByRegex("""ng.bossi.minekraft.*""")
        }
    }
    mavenCentral()
    maven("https://repo.extendedclip.com/releases/")
}

dependencies {
    paperweight.paperDevBundle("1.21.1-R0.1-SNAPSHOT")

    paperLibrary("org.jetbrains.kotlinx", "kotlinx-serialization-json", "1.8.0-RC")

    implementation("ng.bossi.minekraft", "Vanilla", "0.0.1")
    implementation("ng.bossi.minekraft", "Vanilla-Config-Konfig", "0.0.1")
    implementation("ng.bossi.minekraft", "Paper-Core", "0.0.1")

    paperLibrary("dev.jorel", "commandapi-bukkit-shade-mojang-mapped", "9.7.0")
    paperLibrary("dev.jorel", "commandapi-bukkit-kotlin", "9.7.0")

    compileOnly("me.clip", "placeholderapi", "2.11.6")
}

paper {
    name = "MiniTab"
    description = "Simple TabList Header/Footer Plugin. Part of MiniPaper"
    website = "https://mini.bossi.ng/tab"
    author = "Max Bossing"
    prefix = "MiniTab"

    main = "ng.bossi.mini.tab.MiniTab"
    loader = "ng.bossi.mini.tab.MiniTabLoader"

    generateLibrariesJson = true

    serverDependencies {
        register("PlaceholderAPI") {
            required = false
        }
    }

    apiVersion = "1.21"
    load = BukkitPluginDescription.PluginLoadOrder.POSTWORLD
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(22))
        sourceCompatibility = JavaVersion.VERSION_22
        targetCompatibility = JavaVersion.VERSION_22
    }
}

kotlin {
    jvmToolchain {
        languageVersion.set(JavaLanguageVersion.of(22))
    }
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_22)
    }
}

tasks {
    runServer {
        minecraftVersion("1.21.1")
    }
}

