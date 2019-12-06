plugins {
    kotlin("jvm") version "1.3.61"
}

repositories {
    maven(url = "../../repository")
    maven(url = "https://dl.bintray.com/kotlin/kotlin-dev/")
    mavenCentral()
}

dependencies {
    implementation("io.github.bamboo.kotlinconf2019:groddler-kotlin-dsl:1.0-SNAPSHOT")
}
