import org.jetbrains.configurePublication

plugins {
    id("com.github.johnrengelman.shadow")
    id("com.jfrog.bintray")
}

repositories {
    maven(url = "https://dl.bintray.com/kotlin/kotlinx")
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-cli-jvm:0.2.1")
    implementation(project(":core"))
    implementation(kotlin("stdlib"))
}

tasks {
    shadowJar {
        val dokka_version: String by project
        archiveFileName.set("dokka-cli-$dokka_version.jar")
        archiveClassifier.set("")
        manifest {
            attributes("Main-Class" to "org.jetbrains.dokka.MainKt")
        }
    }
}

configurePublication("dokka-cli", useShadow = true)

