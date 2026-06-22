plugins {
    alias(libs.plugins.kotlin.jvm)
}

group = "com.localdirect.desktop"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    //testImplementation(kotlin("test"))
    implementation(libs.kotlinx.coroutines)
}

tasks.test {
    useJUnitPlatform()
}