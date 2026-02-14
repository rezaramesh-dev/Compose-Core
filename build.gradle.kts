plugins {
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.ksp.plugin) apply false
    alias(libs.plugins.hilt.plugin) apply false
    alias(libs.plugins.jetbrains.kotlin.serialization) apply false
    id("maven-publish")
}

/*
•	Timber logging
•	LeakCanary
•	StrictMode
•	Custom Dispatcher Injection
•	Paging 3
•	Coil
•	Splash API
•	App Startup
•	Feature Flags
•	BuildConfig wrapper*/
