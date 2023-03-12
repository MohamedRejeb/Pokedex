import org.jetbrains.kotlin.konan.target.Family
import org.jetbrains.kotlin.gradle.plugin.KotlinTarget
import com.mocoding.pokedex.Deps
import org.jetbrains.kotlin.gradle.plugin.mpp.pm20.util.targets

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
}

group "com.mocoding"
version "1.0-SNAPSHOT"

kotlin {
    js(IR) {
        browser()
        binaries.executable()
    }

    disableCompilationsOfNeeded()

//    targets.forEach { kotlinTarget ->
//        kotlinTarget.compilations.configureEach {
//            println(platformType)
//            if (name != "jsBrowserDevelopmentRun") {
//                compileTaskProvider.configure {
//                    println(name)
////                    this.enabled = name != "compileKotlinJs"
//                }
//            }
//            compileKotlinTask.enabled = platformType != org.jetbrains.kotlin.gradle.plugin.KotlinPlatformType.js
//        }
//    }

    sourceSets {
        val jsMain by getting {
            dependencies {
                implementation(project(":shared"))
//                implementation(compose.web.core)
//                implementation(compose.runtime)
                implementation(enforcedPlatform(Deps.Org.JetBrains.KotlinWrappers.kotlinWrappersBom))
                implementation(Deps.Org.JetBrains.KotlinWrappers.kotlinStyled)
            }
        }

        val jsTest by getting {
            dependencies {
                implementation(kotlin("test-js"))
            }
        }
    }
}

compose.experimental {
    web.application {}
}

object EnvParams {

    val metadataOnly: Boolean get() = System.getProperty("metadata_only") != null
    val splitTargets: Boolean get() = System.getProperty("split_targets") != null
}

internal object Compilations {

    val isGenericEnabled: Boolean get() = isValidOs { it.isLinux }
    val isDarwinEnabled: Boolean get() = isValidOs { it.isMacOsX }
    val isWindowsEnabled: Boolean get() = isValidOs { it.isWindows }

    private fun isValidOs(predicate: (OperatingSystem) -> Boolean): Boolean =
        !EnvParams.splitTargets || predicate(org.gradle.nativeplatform.platform.internal.DefaultNativePlatform.getCurrentOperatingSystem())
}

fun org.jetbrains.kotlin.gradle.dsl.KotlinProjectExtension.disableCompilationsOfNeeded() {
    targets.forEach {
        it.disableCompilationsOfNeeded()
    }
}

fun KotlinTarget.disableCompilationsOfNeeded() {
    val isAllowed = isCompilationAllowed()
    println("$project, $this, compilation allowed: $isAllowed")

    if (!isAllowed) {
        disableCompilations()
    }
}

fun KotlinTarget.disableCompilations() {
    compilations.configureEach {
        compileKotlinTask.enabled = false
    }
}

fun org.jetbrains.kotlin.gradle.plugin.KotlinTarget.isCompilationAllowed(): Boolean =
    when (platformType) {
        org.jetbrains.kotlin.gradle.plugin.KotlinPlatformType.common -> true
        org.jetbrains.kotlin.gradle.plugin.KotlinPlatformType.jvm,
        org.jetbrains.kotlin.gradle.plugin.KotlinPlatformType.js,
        org.jetbrains.kotlin.gradle.plugin.KotlinPlatformType.androidJvm,
        org.jetbrains.kotlin.gradle.plugin.KotlinPlatformType.wasm -> Compilations.isGenericEnabled
        org.jetbrains.kotlin.gradle.plugin.KotlinPlatformType.native -> (this as org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget).isCompilationAllowed()
    }

fun org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget.isCompilationAllowed(): Boolean =
    when (val family = konanTarget.family) {
        Family.OSX,
        Family.IOS,
        Family.TVOS,
        Family.WATCHOS -> Compilations.isDarwinEnabled
        Family.LINUX,
        Family.ANDROID,
        Family.WASM -> Compilations.isGenericEnabled
        Family.MINGW -> Compilations.isWindowsEnabled
        Family.ZEPHYR -> error("Unsupported family: $family")
    }
