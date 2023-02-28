package pl.makenika.kargon2.buildsrc

import org.gradle.api.Project
import org.gradle.api.artifacts.dsl.RepositoryHandler
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import java.net.URI

public fun Project.configureGithubPublishing() {
    extensions.configure(PublishingExtension::class.java) {
        publications.withType(MavenPublication::class.java) {
            configureGithubMavenPom(this@configureGithubPublishing)
        }
        repositories.addGithubRepository()
    }
}

internal fun MavenPublication.configureGithubMavenPom(project: Project) {
    pom {
        name.set(project.name)
        description.set(project.description)
        url.set("https://github.com/armatys/kargon2")

        licenses {
            license {
                name.set("Apache License 2.0")
                url.set("https://www.apache.org/licenses/LICENSE-2.0")
            }
        }
    }
}

internal fun RepositoryHandler.addGithubRepository() {
    maven {
        name = "GitHubPackages"
        credentials {
            username = System.getenv("USERNAME")
            password = System.getenv("TOKEN")
        }
        url = URI("https://maven.pkg.github.com/armatys/kargon2")
    }
}
