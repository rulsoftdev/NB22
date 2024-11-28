plugins {
    id("rulsoft.jvm.library")
    id("rulsoft.jvm.ktor")
    id("rulsoft.di.library")
}
tasks.withType<JavaExec> {
    jvmArgs("-DbuildType=release")
}
dependencies {
    implementation(project(":core"))
    add("implementation", project(":domain"))

    implementation(libs.arrow.core)
}