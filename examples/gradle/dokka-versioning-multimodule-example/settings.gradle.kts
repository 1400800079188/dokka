/*
 * Copyright 2014-2023 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license.
 */

include(":parentProject")
include(":parentProject:childProjectA")
include(":parentProject:childProjectB")

rootProject.name = "dokka-versioning-multimodule-example"

pluginManagement {
    repositories {
        mavenLocal()
        mavenCentral()
    }
}
