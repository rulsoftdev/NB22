import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.rulsoft.ap.nb22.libs

class DiLibraryComposeConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("rulsoft.di.library")

            dependencies{
                add("implementation", libs.findLibrary("koin.compose").get())
            }
        }
    }
}