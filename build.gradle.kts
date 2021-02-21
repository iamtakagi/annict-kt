object Versions {
    const val OkHttp = "4.8.1"
    const val KotlinxCoroutinesCore = "1.4.2"
    const val KotlinxSerializationJson = "1.0.1"
    const val JUnit = "4.12"
}

plugins {
    kotlin("jvm") version "1.4.30"
    kotlin("plugin.serialization") version "1.4.30"
    id("com.github.johnrengelman.shadow") version "6.1.0"

    `maven-publish`
    signing
    id("io.codearte.nexus-staging") version "0.22.0"

    id("org.jetbrains.dokka") version "1.4.20"
}

repositories {
    mavenCentral()
    mavenLocal()
    jcenter()
}

dependencies {
    // Kotlin
    implementation("com.squareup.okhttp3:okhttp:${Versions.OkHttp}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.KotlinxCoroutinesCore}")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.KotlinxSerializationJson}")

    testImplementation("junit:junit:4.12")
}

kotlin {
    target {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
                apiVersion = "1.4"
                languageVersion = "1.4"
                verbose = true
            }
        }
    }

    sourceSets.all {
        languageSettings.progressiveMode = true
        languageSettings.apply {
            useExperimentalAnnotation("kotlinx.coroutines.ExperimentalCoroutinesApi")
            useExperimentalAnnotation("kotlin.io.path.ExperimentalPathApi")
            useExperimentalAnnotation("kotlin.time.ExperimentalTime")
            useExperimentalAnnotation("kotlin.ExperimentalStdlibApi")
        }
    }
}

/*****************
 * Publishing
 *****************/

object Env {
    const val Version = "VERSION"
    const val OSSRHUsername = "OSSRH_USERNAME"
    const val OSSRHPassword = "OSSRH_PASSWORD"
    const val GitHubUsername = "GITHUB_USERNAME"
    const val GitHubPassword = "GITHUB_PASSWORD"
}

object Publications {
    const val GroupId = "com.github.iamtakagi"
    const val OSSRHProfileGroupId = "com.github.iamtakagi"
    const val Description = "Annict API wrapper for Kotlin."
    const val GitHubUsername = "iamtakagi"
    const val GitHubRepository = "kannict"

    const val LicenseName = "The MIT Licence"
    const val LicenseUrl = "https://opensource.org/licenses/MIT"

    const val DeveloperId = "takagi"
    const val DeveloperName = "takagi"
    const val DeveloperEmail = "iam.takagi.dev@gmail.com"

    const val MavenCentralStagingRepositoryUrl = "https://oss.sonatype.org/service/local/staging/deploy/maven2"
    const val MavenCentralSnapshotRepositoryUrl = "https://oss.sonatype.org/content/repositories/snapshots"
    const val GitHubPackagesRepositoryUrl = "https://maven.pkg.github.com/$GitHubUsername/$GitHubRepository"
}

tasks {
    register<Jar>("kdocJar") {
        from(dokkaHtml)
        dependsOn(dokkaHtml)
        archiveClassifier.set("javadoc")
    }
}

publishing {
    repositories {
        maven {
            name = "Sonatype"
            url = uri(
               if (System.getenv(Env.Version).orEmpty().endsWith("-SNAPSHOT")) {
                    Publications.MavenCentralSnapshotRepositoryUrl
                } else {
                    Publications.MavenCentralStagingRepositoryUrl
                }
            )

            credentials {
                username = System.getenv(Env.OSSRHUsername)
                password = System.getenv(Env.OSSRHPassword)
            }
        }

        maven {
            name = "GitHubPackages"
            url = uri(Publications.GitHubPackagesRepositoryUrl)

            credentials {
                username = System.getenv(Env.GitHubUsername)
                password = System.getenv(Env.GitHubPassword)
            }
        }
    }

    publications.withType<MavenPublication> {
        groupId = Publications.GroupId
        artifactId = when (name) {
            "kotlinMultiplatform" -> {
                rootProject.name
            }
            else -> {
                "${rootProject.name}-$name"
            }
        }
        version = System.getenv(Env.Version)

        pom {
            name.set(artifactId)
            description.set(Publications.Description)
            url.set("https://github.com/${Publications.GitHubUsername}/${Publications.GitHubRepository}")

            licenses {
                license {
                    name.set(Publications.LicenseName)
                    url.set(Publications.LicenseUrl)
                }
            }

            developers {
                developer {
                    id.set(Publications.DeveloperId)
                    name.set(Publications.DeveloperName)
                    email.set(Publications.DeveloperEmail)
                }
            }

            scm {
                url.set("https://github.com/${Publications.GitHubUsername}/${Publications.GitHubRepository}")
            }
        }

        artifact(tasks["kdocJar"])
    }
}