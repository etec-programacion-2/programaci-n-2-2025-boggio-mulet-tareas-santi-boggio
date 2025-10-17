val http4kVersion = "5.13.4.0"
plugins {
    kotlin("jvm") version "2.2.0"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    google()
}

dependencies {
    testImplementation(kotlin("test"))
    // Core: Contiene HttpHandler, Request, Response, Status, y Routing
    implementation("org.http4k:http4k-core:$http4kVersion")

    // Necesitas un servidor para ejecutar la aplicación (ej. Jetty)
    implementation("org.http4k:http4k-server-jetty:$http4kVersion")

    implementation("org.http4k:http4k-format-jackson:$http4kVersion")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(11)
}