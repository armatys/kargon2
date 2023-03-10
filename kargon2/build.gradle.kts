import pl.makenika.kargon2.buildsrc.configureGithubPublishing

plugins {
    kotlin("multiplatform")
    id("maven-publish")
}

kotlin {
    explicitApi()

    jvm()
    ios()

    sourceSets {
        val commonMain by getting
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val iosMain by getting {
            dependencies {
                implementation(project(":argon2-ios"))
            }
        }
        val jvmMain by getting {
            dependencies {
                implementation("com.password4j:password4j:1.6.3")
            }
        }
    }
}

configureGithubPublishing()
