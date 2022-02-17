import org.springframework.boot.gradle.tasks.bundling.BootJar
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
plugins {
    id("org.springframework.boot") version "2.6.3"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.5.10"
    kotlin("plugin.spring") version "1.5.10"
    kotlin("plugin.jpa") version "1.5.10"
    kotlin("plugin.allopen") version "1.5.10"
    kotlin("plugin.noarg") version "1.5.10"
    kotlin("kapt") version "1.5.10"
}
tasks.withType<BootJar>{
    enabled = false
}
allprojects {
    repositories {
        mavenCentral()
    }
    group = "com.my"
    version = "demo"
    tasks.withType<JavaCompile> {
        sourceCompatibility = "11"
        targetCompatibility = "11"
    }
    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "11"
        }
    }
    tasks.withType<Test> {
        useJUnitPlatform()
    }
}
subprojects {
    apply {
        plugin("kotlin")
        plugin("kotlin-spring")
        plugin("kotlin-jpa")
        plugin("idea")
        plugin("eclipse")
        plugin("org.springframework.boot")
        plugin("io.spring.dependency-management")
        plugin( "kotlin-allopen")
        plugin( "kotlin-noarg")
        plugin( "kotlin-kapt")
    }
    tasks.withType<JavaCompile> {
        options.compilerArgs = listOf("-Amapstruct.suppressGeneratorTimestamp=true",
        "-Amapstruct.defaultComponentModel=spring")
    }
}