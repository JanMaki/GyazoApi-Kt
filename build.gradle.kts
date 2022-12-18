import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("com.github.johnrengelman.shadow") version "7.1.2"
    id("org.jetbrains.kotlin.jvm") version "1.8.0-RC"
    id("maven-publish")

    kotlin("plugin.serialization") version "1.7.21"
}

group = "com.tesca.gyazo_api_kt"

repositories {
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    //REST
    implementation("com.github.kittinunf.fuel", "fuel", "2.3.1")

    //Json
    implementation("org.jetbrains.kotlinx", "kotlinx-serialization-json", "1.4.1")
}

val jar by tasks.getting(Jar::class) {
    manifest {
        attributes["Main-Class"] = "com.tesca.gyazo_api_kt.GyazoApi"
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_16
    targetCompatibility = JavaVersion.VERSION_16
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = JavaVersion.VERSION_16.toString()
}

tasks.withType<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar> {
    archiveBaseName.set("gyazo_api_kt")
}

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("maven") {
                from(components["kotlin"])
                artifact(tasks["shadowJar"])
            }
        }
    }
}