import org.gradle.api.Plugin
import org.gradle.api.Project
import org.rulsoft.ap.nb22.configureKotlinJvm

class JvmLibraryConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("org.jetbrains.kotlin.jvm")
            }
            configureKotlinJvm()
        }
    }
}