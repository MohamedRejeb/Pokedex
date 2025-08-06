plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    kotlin("plugin.serialization")
    id("com.android.library")
    id("org.jetbrains.compose")
    id("kotlin-parcelize")
    id("app.cash.sqldelight")
}

@OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
kotlin {
    jvm("desktop") {
        jvmToolchain(11)
    }

    androidTarget{
        compilations.all {
            kotlinOptions {
                jvmTarget = "11"
            }
        }
    }

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        summary = "Pokedex the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0.0"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../ios/Podfile")
        framework {
            baseName = "shared"
            isStatic = true
            export(libs.decompose)
            export(libs.essenty.lifecycle)
        }
    }
    
    sourceSets {
        val commonMain by getting {
            dependencies {
                // Compose
                with(compose) {
                    api(runtime)
                    api(foundation)
                    api(material)
                    api(material3)
                    api(materialIconsExtended)
                }

                // Ktor
                api(libs.ktor.client.core)
                api(libs.ktor.serialization.kotlinx.json)
                api(libs.ktor.client.contentNegotiation)
                api(libs.ktor.client.logging)

                // Logback for ktor logging
                implementation(libs.logback.classic)

                // SqlDelight
                api(libs.sqldelight.coroutines.extensions)
                api(libs.sqldelight.primitive.adapters)

                // Koin
                api(libs.koin.core)
                api(libs.koin.test)

                // KotlinX Serialization Json
                implementation(libs.kotlinx.serialization.json)

                // Coroutines
                implementation(libs.kotlinx.coroutines.core)

                // MVIKotlin
                api(libs.mvikotlin)
                api(libs.mvikotlin.main)
                api(libs.mvikotlin.extensions.coroutines)

                // Decompose
                api(libs.decompose)
                api(libs.decompose.extensions.compose)

                // Image Loading
                api(libs.imageLoader)
                implementation(libs.essenty.lifecycle)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependencies {
                // Ktor
                implementation(libs.ktor.client.android)

                // SqlDelight
                implementation(libs.sqldelight.android.driver)

                // Koin
                implementation(libs.koin.android)
            }
        }
        val androidUnitTest by getting

        val desktopMain by getting {
            dependsOn(commonMain)

            dependencies {
                // Ktor
                implementation(libs.ktor.client.java)

                // SqlDelight
                implementation(libs.sqldelight.sqlite.driver)
            }
        }

        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting

        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
            dependencies {
                // Ktor
                implementation(libs.ktor.client.darwin)

                // SqlDelight
                implementation(libs.sqldelight.native.driver)

                // TouchLab
                implementation(libs.touchlab.stately.common)
            }
        }

        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting

        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }

    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.jvmTarget = "11"
    }
}

android {
    namespace = "com.mocoding.pokedex"
    compileSdk = libs.versions.compileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

sqldelight {
    databases {
        create("PokemonDatabase") {
            packageName.set("com.mocoding.pokedex.core.database")
        }
    }
}