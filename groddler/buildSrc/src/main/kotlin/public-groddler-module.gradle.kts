plugins {
    `java-library`
    `maven-publish`
}

val sourcesJar by tasks.registering(Jar::class) {
    archiveClassifier.set("sources")
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
            url = rootProject.uri("../repository")
        }
    }
}
