plugins {
    kotlin("jvm") version "1.4.21"
    kotlin("plugin.serialization") version "1.4.21"
    `maven-publish`
}

repositories {
    mavenCentral()
    maven (url = "https://kotlin.bintray.com/kotlinx" )
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    testCompile("junit:junit:4.12")
    implementation("com.squareup.okhttp3:okhttp:4.8.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.2")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.0.1")
}

val buildJar by tasks.creating(Jar::class) {
    from(tasks.compileKotlin)
}

tasks {
    compileKotlin {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }

    compileTestKotlin {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }

    build {
        dependsOn(buildJar)
    }
}

publishing {
    publications {
        create<MavenPublication>("default") {
            artifact(buildJar)
        }
    }

    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/iamtakagi/annict-kt")
            credentials {
                username = System.getenv("GITHUB_USERNAME")
                password = System.getenv("GITHUB_TOKEN")
            }
        }
    }
}
