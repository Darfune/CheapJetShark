buildscript {


    dependencies {
        classpath ("com.google.gms:google-services:4.4.0")
        classpath ("com.android.tools.build:gradle:8.1.2")
    }

    repositories {
        mavenCentral()
    }
}
// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("org.jetbrains.kotlin.jvm") version "1.9.10"
    id("com.google.dagger.hilt.android") version "2.48" apply false
    id("com.android.application") version "8.1.2" apply false
    id("org.jetbrains.kotlin.android") version "1.9.10" apply false
    id("com.google.devtools.ksp") version "1.9.0-1.0.13" apply false
    id("com.google.gms.google-services") version "4.4.0" apply false
}