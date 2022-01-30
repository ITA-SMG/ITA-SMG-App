val composeVersion: String by project
val composeExtensions: String by project
val composeCompiler: String by project
val decomposeVersion: String by project
val koinVersion: String by project

plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    compileSdk = 31
    defaultConfig {
        applicationId = "com.itechart.smg.android"
        minSdk = 24
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    buildFeatures {
        compose = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    composeOptions {
        kotlinCompilerExtensionVersion = composeCompiler
    }
}

dependencies {
    implementation(project(":shared"))
    implementation("com.google.android.material:material:1.5.0")
    implementation("androidx.appcompat:appcompat:1.4.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.3")

    // Compose
    implementation("androidx.compose.ui:ui:${composeVersion}")
    implementation("androidx.compose.ui:ui-tooling:${composeVersion}")
    implementation("androidx.compose.foundation:foundation:${composeVersion}")
    implementation("androidx.compose.material:material:${composeVersion}")
    implementation("androidx.activity:activity-compose:${composeVersion}")
    implementation("androidx.compose.compiler:compiler:${composeVersion}")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:${composeExtensions}")
    implementation("androidx.navigation:navigation-compose:${composeExtensions}")

    // Decompose (xD)
    implementation("com.arkivanov.decompose:decompose:${decomposeVersion}")
    implementation("com.arkivanov.decompose:extensions-android:${decomposeVersion}")
    implementation("com.arkivanov.decompose:extensions-compose-jetpack:${decomposeVersion}")

    // Koin
    implementation("io.insert-koin:koin-android:${koinVersion}")
    implementation("io.insert-koin:koin-androidx-compose:${koinVersion}")
}