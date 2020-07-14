import org.jetbrains.configurePublication

plugins {
    id("com.github.johnrengelman.shadow")
    `maven-publish`
    id("com.jfrog.bintray")
}

dependencies {
    compileOnly(project(":core"))

    val kotlin_version: String by project
    api("org.jetbrains.kotlin:kotlin-compiler:$kotlin_version")

    api(project(":kotlin-analysis:dependencies", configuration = "shadow"))
}

configurePublication("dokka-analysis")
