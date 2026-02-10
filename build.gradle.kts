plugins {
    alias(libs.plugins.kotlin.jvm)
    //alias(libs.plugins.ktor)
}

group = "com.localdirect.desktop"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    //testImplementation(kotlin("test"))
    implementation(libs.kotlinx.coroutines)

    //implementation(libs.ktor.core)
    //implementation(libs.ktor.netty)
    //implementation(libs.ktor.websockets)
}

tasks.test {
    useJUnitPlatform()
}