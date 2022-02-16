plugins {
    base
    kotlin("jvm") version "1.5.10" apply false
}

allprojects {
    group = "com.my"
    version = "demo"
    repositories {
        mavenCentral()
    }
}