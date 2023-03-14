@file:Suppress("PackageDirectoryMismatch")

package org.gradle.kotlin.dsl // for convenience use a default package for gradle.kts scripts

import org.gradle.api.Project
import org.jetbrains.DokkaBuildProperties

/*
 * Utility functions for accessing Gradle extensions that are created by convention plugins.
 *
 * (Gradle can't generate the nice DSL accessors for the project that defines them)
 *
 * These functions should be marked `internal`, because they are not needed outside of the
 * convention plugins project.
 */

/**
 * Retrieves the [dokkaBuild][org.jetbrains.DokkaBuildProperties] extension.
 */
internal val Project.dokkaBuild: DokkaBuildProperties
    get() = extensions.getByType()

/**
 * Configures the [dokkaBuild][org.jetbrains.DokkaBuildProperties] extension.
 */
internal fun Project.dokkaBuild(configure: DokkaBuildProperties.() -> Unit) =
    extensions.configure(configure)
