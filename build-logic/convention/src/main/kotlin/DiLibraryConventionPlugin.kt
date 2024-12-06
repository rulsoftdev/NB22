import org.gradle.api.Plugin
import org.gradle.api.Project
import org.rulsoft.ap.nb22.libs
import org.gradle.kotlin.dsl.dependencies

class DiLibraryConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.google.devtools.ksp")
            }

            dependencies {
                //Koin
                add("implementation", platform(libs.findLibrary("koin.bom").get()))
                add("implementation", libs.findLibrary("koin.core").get())
                add("implementation", libs.findLibrary("koin.annotations").get())
                add("ksp", libs.findLibrary("koin.compiler").get())
            }
        }
    }
}