package org.rulsoft.ap.nb22.data.common

import java.util.*

object Config {
    private const val DEFAULT_FILE = "config.properties"
    private const val RELEASE_FILE = "config-release.properties"

    private val properties: Properties = Properties().apply {
        val buildType = System.getProperty("buildType", "debug")
        val fileName = if (buildType == "release") RELEASE_FILE else DEFAULT_FILE
        val inputStream = Config::class.java.classLoader.getResourceAsStream(fileName)
            ?: throw IllegalArgumentException("Archivo de configuración no encontrado: $fileName")
        load(inputStream)
    }

    val BASE_URL: String get() = properties.getProperty("BASE_URL")
    val URI_PLAYSTORE: String get() = properties.getProperty("URI_PLAYSTORE")
    val API_KEY: String get() = properties.getProperty("API_KEY")

}