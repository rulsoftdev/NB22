plugins {
    id("rulsoft.android.library")
    id("rulsoft.android.room")
    id("rulsoft.di.library")
}
android {
    namespace = "org.rulsoft.ap.nb22.data.android"
}
dependencies {
    implementation(project(":domain"))
    add("implementation", project(":data"))
}