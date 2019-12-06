plugins {
    id("org.jetbrains.intellij") version "0.4.14"
    kotlin("jvm")
}

dependencies {
    api(project(":turtle-scripting"))
    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("scripting-common"))
    testImplementation("junit", "junit", "4.12")
}

// See https://github.com/JetBrains/gradle-intellij-plugin/
intellij {
    version = "2019.3"

    setPlugins(
        "gradle",
        "org.jetbrains.kotlin:1.3.61-release-IJ2019.3-1",
        "IdeaVIM:0.54"
    )
}

tasks {

    patchPluginXml {
        changeNotes(
            """
Add change notes here.<br>
<em>most HTML tags may be used</em>
"""
        )
    }
}
