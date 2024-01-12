/**
 * Gradle Build Cache conventions.
 *
 * See [KotlinBuildProperties] for properties.
 *
 * https://github.com/JetBrains/kotlin/blob/2675531624d42851af502a993bbefd65ee3e38ef/repo/gradle-settings-conventions/build-cache/src/main/kotlin/build-cache.settings.gradle.kts
 */

val buildProperties = getKotlinBuildPropertiesForSettings(settings)

buildCache {
    local {
        isEnabled = buildProperties.localBuildCacheEnabled
        if (buildProperties.localBuildCacheDirectory != null) {
            directory = buildProperties.localBuildCacheDirectory
        }
    }

    val remoteBuildCacheUrl = buildProperties.buildCacheUrl?.trim()
    if (!remoteBuildCacheUrl.isNullOrEmpty()) {
        remote<HttpBuildCache> {
            url = uri(remoteBuildCacheUrl)
            isPush = buildProperties.pushToBuildCache
            if (buildProperties.buildCacheUser != null &&
                buildProperties.buildCachePassword != null
            ) {
                credentials.username = buildProperties.buildCacheUser
                credentials.password = buildProperties.buildCachePassword
            }
        }
    }
}
