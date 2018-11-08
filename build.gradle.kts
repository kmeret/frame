import Config

buildscript {
    repositories {
        mavenLocal()
        google()
        jcenter()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:${Config.studioVersion}")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Config.kotlinVersion}")
    }
}

allprojects {
    repositories {
        mavenLocal()
        google()
        jcenter()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
