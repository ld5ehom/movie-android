rootProject.name = "MovieApplication"
include(":app")

include(":domain")
include(":data")
include(":remote")
include(":local")
include(":design")
include(":device")
include(":common")

include(":component")
include(":presentation")
include(":ui")
include(":data-resource")


pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        google()
        jcenter()
    }
}
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
