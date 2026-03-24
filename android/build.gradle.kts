plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.mocoding.pokedex.android"
    compileSdk = libs.versions.compileSdk.get().toInt()
    defaultConfig {
        applicationId = "com.mocoding.pokedex.android"
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionCode = libs.versions.appVersionCode.get().toInt()
        versionName = "${libs.versions.appMajorVersion.get()}.${libs.versions.appMinorVersion.get()}.${libs.versions.appPatchVersion.get()}"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.composeCompiler.get()
    }
    packaging {
        resources {
            pickFirsts.add(
                "META-INF/INDEX.LIST"
            )
            excludes.addAll(
                listOf(
                    "META-INF/AL2.0",
                    "META-INF/LGPL2.1",
                ),
            )
        }
    }
    buildTypes {

        getByName("release") {
            isMinifyEnabled = true
        }

        getByName("debug") {
            isMinifyEnabled = false
        }

    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    implementation(project(":shared"))
    implementation(libs.androidx.activity.compose)

    // Koin
    api(libs.koin.android)
}