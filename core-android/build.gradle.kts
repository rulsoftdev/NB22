plugins {
    id("rulsoft.android.library")
    id("rulsoft.di.library")
}
android {
    namespace = "org.rulsoft.ap.nb22.core.android"
}
dependencies {
    //Módulo
    implementation(project(":core"))

    //Firebase
    implementation(platform(libs.firebase.bom))

    //Crashlytics y analytics
    implementation(libs.firebase.crashlytics.ktx)
    implementation(libs.firebase.analytics.ktx)
}