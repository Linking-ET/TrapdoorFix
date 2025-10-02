plugins {
    java
    id("io.typst.spigradle") version "3.0.5"
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "let.xiaochen"
version = "2.0-SNAPSHOT"

repositories {
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    maven("https://repo.papermc.io/repository/maven-public/")
    mavenCentral()
    mavenLocal()
}

dependencies {
    compileOnly("org.spigotmc:spigot-api:1.18.2-R0.1-SNAPSHOT")
    compileOnly("org.jetbrains:annotations:24.0.1")
}

spigot {
    main.set("let.xiaochen.trapdoorfix.TrapdoorFix")
    authors = listOf("XChen446")
    apiVersion = "1.13"
    prefix = "TrapdoorFix"
    description.set("不那么优雅的活版门跳略修复")
    commands {
        create("trapdoorfix") {
            aliases = listOf("tdf")
            description = "重载 TrapdoorFix 配置"
            permission = "trapdoorfix.reload"
            usage = "/<command> reload"
        }
    }
    permissions {
        create("trapdoorfix.reload") {
            description = "允许重载配置"
            defaults = "op"
        }
    }
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

tasks {
    shadowJar {
        archiveClassifier.set("")
        destinationDirectory.set(file("$rootDir/release"))
    }
    build {
        dependsOn(shadowJar)
    }
}

private fun String?.set(string: String) {}
