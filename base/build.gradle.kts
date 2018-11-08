/*

    ./gradlew :base:clean :base:build :base:publish

    implementation project(":base")

    implementation "io.github.kmeret:base:1.0.0"

 */

import Config

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("maven-publish")
}

android {
    compileSdkVersion(Config.maxSdk)

    defaultConfig {
        minSdkVersion(Config.minSdk)
        targetSdkVersion(Config.maxSdk)
        versionCode = Config.libraryCode
        versionName = Config.libraryVersion
        vectorDrawables.useSupportLibrary = true
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    sourceSets {
        getByName("main").java.srcDirs("src/main/kotlin")
        getByName("test").java.srcDirs("src/test/kotlin")
        getByName("androidTest").java.srcDirs("src/androidTest/kotlin")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Config.kotlinVersion}")

    implementation("com.squareup.retrofit2:retrofit:${Config.retrofitVersion}")
    implementation("com.squareup.retrofit2:converter-gson:${Config.retrofitVersion}")
    implementation("com.squareup.okhttp3:logging-interceptor:3.11.0")
    implementation("com.squareup.picasso:picasso:2.71828")

    implementation("androidx.appcompat:appcompat:${Config.androidxVersion}")
    implementation("androidx.recyclerview:recyclerview:1.0.0")

    testImplementation("junit:junit:4.12")
    androidTestImplementation("androidx.test:runner:1.1.0")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.1.0")
}

publishing {
    repositories {
        mavenLocal()
    }
    publications {
        register("aar", MavenPublication::class) {
            groupId = Config.domainName
            artifactId = project.name
            version = Config.libraryVersion
            artifact("$buildDir/outputs/aar/${project.name}-debug.aar")
        }
    }
}
