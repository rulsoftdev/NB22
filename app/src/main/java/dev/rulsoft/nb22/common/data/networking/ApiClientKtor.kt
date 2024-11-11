package dev.rulsoft.nb22.common.data.networking

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.statement.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import dev.rulsoft.nb22.BuildConfig
import dev.rulsoft.nb22.common.data.networking.serializers.DateSerializer
import dev.rulsoft.nb22.common.data.util.ApiConstants
import io.ktor.client.plugins.defaultRequest
import kotlinx.serialization.modules.SerializersModule
import java.util.Date

object ApiClientKtor {

    private val json = Json {
        ignoreUnknownKeys = true
        serializersModule = SerializersModule {
            contextual(Date::class, DateSerializer)
        }
        coerceInputValues = true
    }

    val httpClient = HttpClient(OkHttp) {
        install(ContentNegotiation) {
            json(json)
        }
        // Agrega el plugin de logging
        install(Logging) {
            level = LogLevel.ALL // Puedes cambiar a HEADERS, BODY, o INFO según lo que necesites
            logger = Logger.SIMPLE // Puedes personalizar el logger si lo deseas
        }
        defaultRequest {
            url(ApiConstants.BASE_URL)
            header("x-api-key", ApiConstants.API_KEY)
        }
    }

    suspend inline fun <reified T> get(path: String): Result<T> {
        return try {
            val response: HttpResponse = httpClient.get(path)
            if (response.status.value in 200..299) {
                Result.success(response.body())
            } else {
                Result.failure(Throwable("Error: ${response.status}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend inline fun <reified T> post(path: String, body: Any): Result<T> {
        return try {
            val response: HttpResponse = httpClient.post(path) {
                setBody(body)
            }

            if (response.status.value in 200..299) {
                Result.success(response.body())
            } else {
                Result.failure(Throwable("Error: ${response.status}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
