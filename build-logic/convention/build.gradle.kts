plugins {
    `kotlin-dsl`
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "rulsoft.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidApplicationCompose") {
            id = "rulsoft.android.application.compose"
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }
        register("androidLibraryCompose") {
            id = "rulsoft.android.library.compose"
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }
        register("androidLibrary") {
            id = "rulsoft.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("androidRoom") {
            id = "rulsoft.android.room"
            implementationClass = "AndroidRoomConventionPlugin"
        }
        register("jvmKtor") {
            id = "rulsoft.jvm.ktor"
            implementationClass = "JvmKtorConventionPlugin"
        }
        register("jvmLibrary") {
            id = "rulsoft.jvm.library"
            implementationClass = "JvmLibraryConventionPlugin"
        }
        register("jvmFirebase") {
            id = "rulsoft.jvm.firebase"
            implementationClass = "JvmFirebaseConventionPlugin"
        }
        register("diLibrary") {
            id = "rulsoft.di.library"
            implementationClass = "DiLibraryConventionPlugin"
        }
        register("diLibraryCompose") {
            id = "rulsoft.di.library.compose"
            implementationClass = "DiLibraryComposeConventionPlugin"
        }
    }
}