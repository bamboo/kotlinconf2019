import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    // Development version of Kotlin required for supporting
    // classpath update from refineConfiguration / beforeCompiling
    kotlin("jvm") version "1.3.70-dev-2487" apply false
}

subprojects {

    group = "io.github.bamboo.kotlinconf2019"

    version = "1.0-SNAPSHOT"

    repositories {
        maven(url = "https://dl.bintray.com/kotlin/kotlin-dev/")
        mavenCentral()
    }

    apply(plugin = "java")

    configure<JavaPluginConvention> {
        sourceCompatibility = JavaVersion.VERSION_1_8
    }

    tasks {

        // Preserve parameter names in the bytecode
        withType<JavaCompile>().configureEach {
            options.compilerArgs.add("-parameters")
        }

        withType<KotlinCompile>().configureEach {
            kotlinOptions {
                jvmTarget = "1.8"
                freeCompilerArgs = listOf("-java-parameters")
            }
        }
    }
}
