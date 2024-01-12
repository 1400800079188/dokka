/**
 * Gradle Enterprise conventions.
 *
 * See [KotlinBuildProperties] for properties.
 *
 * Copied from https://github.com/JetBrains/kotlin/blob/19073b96a7ed53dbda61337465ca898c1482e090/repo/gradle-settings-conventions/gradle-enterprise/src/main/kotlin/gradle-enterprise.settings.gradle.kts
 */

plugins {
    id("com.gradle.enterprise")
    id("com.gradle.common-custom-user-data-gradle-plugin") apply false
}

val buildProperties = getKotlinBuildPropertiesForSettings(settings)

val buildScanServer = buildProperties.buildScanServer

if (buildProperties.buildScanServer != null) {
    plugins.apply("com.gradle.common-custom-user-data-gradle-plugin")
}

gradleEnterprise {
    buildScan {
        if (buildScanServer != null) {
            server = buildScanServer
            publishAlways()

            capture {
                isTaskInputFiles = true
                isBuildLogging = true
                isBuildLogging = true
                isUploadInBackground = true
            }
        } else {
            termsOfServiceUrl = "https://gradle.com/terms-of-service"
            termsOfServiceAgree = "yes"
        }

        val overriddenName = (buildProperties.getOrNull("kotlin.build.scan.username") as? String)?.trim()
        val isTeamCity = buildProperties.isTeamcityBuild
        obfuscation {
            ipAddresses { _ -> listOf("0.0.0.0") }
            hostname { _ -> "concealed" }
            username { originalUsername ->
                when {
                    isTeamCity -> "TeamCity"
                    overriddenName.isNullOrEmpty() -> "concealed"
                    overriddenName == "<default>" -> originalUsername
                    else -> overriddenName
                }
            }
        }
    }
}
