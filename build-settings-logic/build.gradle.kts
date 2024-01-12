/*
 * Copyright 2014-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
 */

plugins {
    `kotlin-dsl`
}

description = "Conventions for use in settings.gradle.kts scripts"

dependencies {
    implementation(libs.kotlin.buildGradlePlugin)
    implementation(libs.gradlePlugin.gradle.enterprise)
    implementation(libs.gradlePlugin.gradle.customUserData)
}
