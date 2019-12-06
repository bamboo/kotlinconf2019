plugins {
    kotlin("jvm")
    `public-groddler-module`
}

dependencies {

    api(project(":groddler-core"))
    api(kotlin("scripting-common"))

    implementation(project(":groddler-extensions"))
    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))

    implementation(kotlin("scripting-jvm"))
    implementation(kotlin("scripting-jvm-host-embeddable"))
    implementation(kotlin("scripting-compiler-impl-embeddable") as String) {
        isTransitive = false
    }

    testImplementation("junit:junit:4.12")
    testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0")
}
