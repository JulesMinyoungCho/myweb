plugins {
    base
    kotlin("jvm") version "1.5.10"
}

allprojects {
    group = "com.my"
    version = "demo"
    repositories {
        mavenCentral()
    }
}