plugins {
    kotlin("jvm") version "1.9.0"
    application
}

group = "com.dicoding.kotlin"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("it.skrape:skrapeit:1.2.2")
    implementation("org.seleniumhq.selenium:selenium-java:4.5.0")
    implementation("org.seleniumhq.selenium:selenium-http-jdk-client:4.5.0")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(8)
}

application {
    mainClass.set("MainKt")
}