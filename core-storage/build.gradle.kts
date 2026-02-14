plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.ksp.plugin)
    alias(libs.plugins.hilt.plugin)
    alias(libs.plugins.jetbrains.kotlin.serialization)
}

android {
    namespace = "com.onestackdev.core.storage"
    compileSdk {
        version = release(36) {
            minorApiLevel = 1
        }
    }

    defaultConfig {
        minSdk = 24
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_22
        targetCompatibility = JavaVersion.VERSION_22
    }
}

dependencies {

    //Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

    //Room Database
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    ksp(libs.room.compiler)

    //Datastore
    implementation(libs.data.store)

    //Serialization
    implementation(libs.kotlinx.serialization.json)
}