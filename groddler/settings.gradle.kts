rootProject.name = "kotlinconf2019-groddler"

include(
    "groddler-core",
    "groddler-kotlin-dsl",
    "groddler-extensions"
)

pluginManagement {
    repositories {
        maven(url = "https://dl.bintray.com/kotlin/kotlin-dev/")
        gradlePluginPortal()
    }
}
