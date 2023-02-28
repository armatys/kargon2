plugins {
    kotlin("multiplatform") version "1.8.10" apply false
}

allprojects {
    group = "pl.makenika.kargon2"
    version = System.getenv("VERSION") ?: "1.0-SNAPSHOT"

    repositories {
        google()
        mavenCentral()
    }
}
