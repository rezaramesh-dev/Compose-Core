plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.ksp.plugin)
    alias(libs.plugins.hilt.plugin)
    id("maven-publish")
}

android {
    namespace = "com.onestackdev.core.common"
    compileSdk {
        version = release(36) {
            minorApiLevel = 1
        }
    }

    defaultConfig {
        minSdk = 26
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    publishing {
        singleVariant("release") {
            withSourcesJar()
        }
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)

    //Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

    implementation(libs.viewmodel.ktx)
    implementation(libs.lifecycle.ktx)

    implementation(libs.kotlinx.coroutines)

    //Retrofit
    implementation(libs.retrofit)
}

publishing {
    publications {
        register<MavenPublication>("release") {
            groupId = "com.github.rezaramesh"
            artifactId = "core-common"
            version = "1.0.0"

            afterEvaluate {
                from(components["release"])
            }
        }
    }
}