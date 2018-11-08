import Config

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
}

android {
    compileSdkVersion(Config.maxSdk)

    defaultConfig {
        applicationId = "${Config.domainName}.demo"
        minSdkVersion(Config.minSdk)
        targetSdkVersion(Config.maxSdk)
        versionCode = Config.libraryCode
        versionName = Config.libraryVersion
        vectorDrawables.useSupportLibrary = true
        multiDexEnabled = false
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    sourceSets {
        getByName("main").java.srcDirs("src/main/kotlin")
        getByName("test").java.srcDirs("src/test/kotlin")
        getByName("androidTest").java.srcDirs("src/androidTest/kotlin")
    }

    signingConfigs {
        create("kmeret") {
            try {
                storeFile = file("keystore")
                storePassword = project.properties["KEYSTORE_PASSWORD"] as String
                keyAlias = "key"
                keyPassword = project.properties["KEY_PASSWORD"] as String
            }
            catch (ignored: Exception) {
                throw Exception("You should define KEYSTORE_PASSWORD and KEY_PASSWORD " +
                        "in global gradle.properties file.")
            }
        }
    }

    buildTypes {
        getByName("debug") {
            signingConfig = signingConfigs.getByName("kmeret")
            isMinifyEnabled = false
            isShrinkResources = false
        }
        getByName("release") {
            signingConfig = signingConfigs.getByName("kmeret")
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

//    implementation("androidx.multidex:multidex:2.0.0")

    implementation(project(":base"))

    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Config.kotlinVersion}")

    implementation("com.google.dagger:dagger-android-support:${Config.daggerVersion}")

    kapt("com.google.dagger:dagger-compiler:${Config.daggerVersion}")

    implementation("com.squareup.retrofit2:retrofit:${Config.retrofitVersion}")
    implementation("com.squareup.retrofit2:converter-gson:${Config.retrofitVersion}")
    implementation("com.squareup.okhttp3:okhttp:3.11.0")

    implementation("androidx.room:room-runtime:${Config.roomVersion}")
    kapt("androidx.room:room-compiler:${Config.roomVersion}")

    implementation("androidx.appcompat:appcompat:${Config.androidxVersion}")
    implementation("androidx.constraintlayout:constraintlayout:1.1.3")
    implementation("com.google.android.material:material:1.1.0-alpha01")

    testImplementation("junit:junit:4.12")
    androidTestImplementation("androidx.test:runner:1.1.0")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.1.0")
}
