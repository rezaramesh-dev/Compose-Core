plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.ksp.plugin)
    alias(libs.plugins.hilt.plugin)
    id("maven-publish")
}

android {
    namespace = "com.onestackdev.core.ui"
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
    //Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
}

publishing {
    publications {
        register<MavenPublication>("release") {
            groupId = "com.github.rezaramesh"
            artifactId = "core-ui"
            version = "1.0.0"

            afterEvaluate {
                from(components["release"])
            }
        }
    }
}