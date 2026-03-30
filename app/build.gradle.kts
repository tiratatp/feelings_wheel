plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.compose")
    id("app.cash.paparazzi") version "1.3.5"
    id("com.github.triplet.play")
}

fun gitTagCount(): Int =
    try {
        val process =
            ProcessBuilder("git", "tag", "--list")
                .directory(rootProject.projectDir)
                .start()
        process.inputStream
            .bufferedReader()
            .readLines()
            .size
    } catch (_: Exception) {
        0
    }

fun gitVersionName(): String =
    try {
        val process =
            ProcessBuilder("git", "describe", "--tags", "--abbrev=0")
                .directory(rootProject.projectDir)
                .start()
        process.inputStream
            .bufferedReader()
            .readLine()
            ?.trimStart('v')
            ?.trim() ?: "1.0"
    } catch (_: Exception) {
        "1.0"
    }

android {
    namespace = "com.nuttyknot.feelingswheel"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.nuttyknot.feelingswheel"
        minSdk = 26
        targetSdk = 35
        versionCode = maxOf(gitTagCount(), 1)
        versionName = gitVersionName()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        resourceConfigurations += listOf("en", "th")
    }

    signingConfigs {
        val keystoreFile = System.getenv("KEYSTORE_FILE")
        if (keystoreFile != null) {
            create("release") {
                storeFile = file(keystoreFile)
                storePassword = System.getenv("KEYSTORE_PASSWORD")
                keyAlias = System.getenv("KEY_ALIAS")
                keyPassword = System.getenv("KEY_PASSWORD")
            }
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
            signingConfig = signingConfigs.findByName("release")
                ?: signingConfigs.getByName("debug")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
}

play {
    track.set("internal")
    defaultToAppBundles.set(true)
    val jsonFile = System.getenv("PLAY_STORE_SERVICE_ACCOUNT_JSON")
    if (jsonFile != null) {
        serviceAccountCredentials.set(file(jsonFile))
    }
}

tasks.whenTaskAdded {
    if (name == "installDebug") {
        dependsOn("ktlintCheck", "lintDebug", "detekt")
    }
}

dependencies {
    val composeBom = platform("androidx.compose:compose-bom:2025.02.00")
    implementation(composeBom)

    implementation("androidx.core:core-ktx:1.16.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.9.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.9.0")
    implementation("androidx.activity:activity-compose:1.10.1")

    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.animation:animation")

    implementation("androidx.navigation:navigation-compose:2.8.9")
    implementation("androidx.datastore:datastore-preferences:1.1.4")

    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    implementation("androidx.appcompat:appcompat:1.7.1")
    implementation("androidx.core:core-splashscreen:1.2.0")

    testImplementation("junit:junit:4.13.2")
}
