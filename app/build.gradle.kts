plugins {
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ksp)
    alias(libs.plugins.google.services)
    alias(libs.plugins.firebase.crashlytics)
    id("rulsoft.android.application")
    id("rulsoft.android.application.compose")
    id("rulsoft.di.library.compose")
}

android {
    namespace = "org.rulsoft.ap.nb22"

    defaultConfig {
        applicationId = "org.rulsoft.ap.nb22"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildFeatures {
        buildConfig = true
    }
    flavorDimensions += "version"
    productFlavors {
        create("free") {
            dimension = "version"
            applicationIdSuffix = ".free"
            versionCode = 15
            versionName = "${defaultConfig.versionName}-free-${defaultConfig.versionCode}"
            resValue("string", "APP_NAME", "NB22")
        }
        create("pro") {
            dimension = "version"
            applicationIdSuffix = ".pro"
            versionCode = 1
            versionName = "${defaultConfig.versionName}-pro-${defaultConfig.versionCode}"
            resValue("string", "APP_NAME", "NB22 Profesional")
        }
    }
    buildTypes {
        release {
            isDebuggable = false
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "BASE_URL", "\"https://ovhcontrol.rulsoft.org/api.academian22/\"")
            buildConfigField("String", "URI_PLAYSTORE", "\"https://play.google.com/store/apps/details?id=org.rulsoft.cristalpedia\"")
        }
        debug {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "BASE_URL", "\"https://ovhcontrol.rulsoft.org/api.academian22/\"")
            //buildConfigField("String", "BASE_URL", "\"http://192.168.1.200:1004/api.academian22/\"")
            buildConfigField("String", "URI_PLAYSTORE", "\"https://play.google.com/store/apps/details?id=org.rulsoft.cristalpedia\"")
        }
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    //Módulos
    implementation(project(":domain"))
    implementation(project(":presentation"))
    implementation(project(":core-android"))
    implementation(project(":core"))
    implementation(project(":data"))
    implementation(project(":data-android"))

    implementation(libs.androidx.activity.compose)

    implementation(libs.slf4j.jdk14)
    implementation(libs.firebase.common.ktx)
    implementation(libs.google.firebase.crashlytics.ktx)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)

}