plugins {
    kotlin("jvm") version "1.3.61"
}

repositories {
    maven(url = "../../repository")
    mavenCentral()
}

dependencies {
    implementation("io.github.bamboo.kotlinconf2019:turtle-scripting:1.0-SNAPSHOT")
}
