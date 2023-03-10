import pl.makenika.kargon2.buildsrc.configureGithubPublishing

plugins {
    kotlin("multiplatform")
    id("maven-publish")
}

kotlin {
    ios {
        val main by compilations.getting
        val argon2 by main.cinterops.creating {
            compilerOpts += listOf("-Iinclude")
        }
    }

    sourceSets {
        val commonMain by getting
        val iosMain by getting
    }
}

configureGithubPublishing()
