plugins {
    id("rulsoft.jvm.library")
    id("rulsoft.di.library")
}

dependencies {
    //Módulos
    implementation(project(":core"))
    //Kotlin
    implementation(libs.kotlin.reflect)

    implementation(libs.arrow.core)
}