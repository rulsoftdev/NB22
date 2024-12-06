import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.rulsoft.ap.nb22.libs

class JvmFirebaseConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager){
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
            }

            dependencies {
                //Firebase
                add("implementation", libs.findLibrary("firebase.bom").get())

                //Crashlytics y analytics
                add("implementation", libs.findLibrary("firebase.crashlytics.ktx").get())
                add("implementation", libs.findLibrary("firebase.analytics.ktx").get())
            }
        }
    }
}