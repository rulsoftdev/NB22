plugins {
    alias(libs.plugins.kotlin.serialization)
    id("rulsoft.android.library.compose")
    id("rulsoft.di.library.compose")
}
android {
    namespace = "org.rulsoft.ap.nb22.presentation"
    compileSdk = 34

    defaultConfig {
        minSdk = 24
        buildConfigField("String", "APPLICATION_ID", "\"org.rulsoft.ap.nb22\"")
    }
    buildFeatures {
        buildConfig = true
    }

    buildTypes {
        release {
            buildConfigField("String", "BASE_URL", "\"https://ovhcontrol.rulsoft.org/api.academian22/\"")
            buildConfigField("String", "URI_PLAYSTORE", "\"https://play.google.com/store/apps/details?id=org.rulsoft.cristalpedia\"")
        }
        debug {
            buildConfigField("String", "BASE_URL", "\"https://ovhcontrol.rulsoft.org/api.academian22/\"")
            //buildConfigField("String", "BASE_URL", "\"http://192.168.1.200:1004/api.academian22/\"")
            buildConfigField("String", "URI_PLAYSTORE", "\"https://play.google.com/store/apps/details?id=org.rulsoft.cristalpedia\"")
        }
    }
}
dependencies {
    implementation(project(":domain"))
    implementation(project(":core"))
    //Navigation
    implementation(libs.androidx.navigation.compose)

    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    //Material 3
    implementation (libs.androidx.material.icons.extended)

    //Serialización
    implementation(libs.kotlinx.serialization.json)

    //Funciones extras para Kotlin
    implementation(libs.arrow.core)

    // Captura Screen
    implementation(libs.shreyaspatil.capturable)
}