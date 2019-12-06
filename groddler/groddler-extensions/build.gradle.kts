plugins {
    kotlin("jvm")
    `public-groddler-module`
}

dependencies {
    api(project(":groddler-core"))
    implementation(kotlin("stdlib-jdk8"))
}
