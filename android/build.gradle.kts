import com.mocoding.pokedex.Configuration
import com.mocoding.pokedex.Deps
import com.mocoding.pokedex.Versions

plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    namespace = "com.mocoding.pokedex.android"
    compileSdk = Configuration.compileSdk
    defaultConfig {
        applicationId = "com.mocoding.pokedex.android"
        minSdk = Configuration.minSdk
        targetSdk = Configuration.targetSdk
        versionCode = Configuration.versionCode
        versionName = Configuration.versionName
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.composeCompiler
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
    kotlinOptions {
        jvmTarget = "21"
    }
}

dependencies {
    implementation(project(":shared"))
    implementation("androidx.activity:activity-compose:1.8.0")

    // Koin
    with(Deps.Koin) {
        api(android)
    }
}