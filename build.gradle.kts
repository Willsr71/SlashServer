plugins {
    java
    idea
    id("net.kyori.blossom") version "2.1.0"
}

val buildNumber: String = System.getenv("BUILD_NUMBER") ?: "SNAPSHOT"

group = "sr.will"
version = "1.1-$buildNumber"
description = "Adds server aliases"

repositories {
    mavenCentral()

    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    val velocityVersion = "3.3.0-SNAPSHOT"

    compileOnly("com.velocitypowered:velocity-api:$velocityVersion")
    annotationProcessor("com.velocitypowered:velocity-api:$velocityVersion")
}

sourceSets {
    main {
        blossom {
            javaSources {
                property("version", project.version.toString())
                property("description", project.description)
            }
        }
    }
}

java {
    toolchain.languageVersion = JavaLanguageVersion.of(21)
}