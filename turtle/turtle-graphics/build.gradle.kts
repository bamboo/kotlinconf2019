plugins {
    kotlin("jvm")
    `maven-publish`
}

dependencies {

    implementation(kotlin("stdlib-jdk8"))

    testImplementation("junit:junit:4.12")
    testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0")
}

val sourcesJar by tasks.registering(Jar::class) {
    classifier = "sources"
    from(sourceSets.main.get().allSource)
}

publishing {
    publications {
        register<MavenPublication>("library") {
            from(components["java"])
            artifact(sourcesJar.get())
        }
    }
    repositories {
        maven {
            name = "test"
            url = uri("../../repository")
        }
    }
}
