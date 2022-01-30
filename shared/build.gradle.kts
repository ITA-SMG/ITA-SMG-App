val decomposeVersion: String by project
val coroutinesVersion: String by project
val kermitVersion: String by project
val serializationVersion: String by project
val kodeinVersion: String by project
val apolloVersion: String by project
val sharedVersion: String by project
val settingsVersion: String by project
val deploymentTarget: String by project
val koinVersion: String by project
val androidSecurityVersion: String by project

plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
    // Due to problems in DSL we cant place apolloVersion variable here now
    id("com.apollographql.apollo3").version("3.0.0")
    kotlin("plugin.serialization").version("1.6.10")
    id("kotlin-parcelize")
}

version = sharedVersion

kotlin {
    android()
    iosX64()
    iosArm64()
    //iosSimulatorArm64() sure all ios dependencies support this target

    cocoapods {
        summary = "SMG Shared Module"
        homepage = "https://github.com/TheLonelyAstronaut/ITA-SMG-App"
        ios.deploymentTarget = deploymentTarget
        podfile = project.file("../ios/Podfile")
        framework {
            baseName = "shared"
            transitiveExport = true
            export("com.arkivanov.decompose:decompose:${decomposeVersion}")
        }
    }
    
    sourceSets {
        val commonMain by getting {
            dependencies {
                api("com.arkivanov.decompose:decompose:${decomposeVersion}")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${coroutinesVersion}")
                implementation("co.touchlab:kermit:${kermitVersion}")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:${serializationVersion}")
                implementation("com.apollographql.apollo3:apollo-runtime:${apolloVersion}")
                implementation("com.russhwolf:multiplatform-settings:${settingsVersion}")
                implementation("io.insert-koin:koin-core:${koinVersion}")
            }
        }

        val androidMain by getting {
            dependencies {
                implementation("androidx.security:security-crypto:${androidSecurityVersion}")
            }
        }

        val iosX64Main by getting
        val iosArm64Main by getting
        //val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            //iosSimulatorArm64Main.dependsOn(this)
        }
    }
}

android {
    compileSdk = 31
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 24
        targetSdk = 31
    }
}

apollo {
    packageName.set("by.itechart.smg.types.graphql.generated")
}