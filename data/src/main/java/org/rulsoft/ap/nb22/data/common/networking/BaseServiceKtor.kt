package org.rulsoft.ap.nb22.data.common.networking

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import org.rulsoft.ap.nb22.data.common.networking.serializers.DateSerializer
import org.rulsoft.ap.nb22.data.common.util.ApiConstants
import java.util.Date

open class BaseServiceKtor() {

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

    protected suspend inline fun <reified T> get(path: String): Response<T> {
        return try {
            val response: HttpResponse = httpClient.get(path)
            if (response.status.value in 200..299) {
                Response(data = response.body(), statusCode = response.status.value)
            } else {
                Response(data = null, statusCode = response.status.value, errorBody = response.bodyAsText())
            }
        } catch (e: Exception) {
            Response(data = null, statusCode = -1, errorBody = e.message)
        }
    }

    protected suspend inline fun <reified T> post(path: String, body: Any): Response<T> {
        return try {
            val response: HttpResponse = httpClient.post(path) {
                setBody(body)
                contentType(ContentType.Application.Json)
            }

            if (response.status.value in 200..299) {
                Response(data = response.body(), statusCode = response.status.value)
            } else {
                Response(data = null, statusCode = response.status.value, errorBody = response.bodyAsText())
            }
        } catch (e: Exception) {
            Response(data = null, statusCode = -1, errorBody = e.message)
        }
    }
    protected suspend inline fun <reified T> put(path: String, body: Any): Response<T> {
        return try {
            val response: HttpResponse = httpClient.post(path) {
                setBody(body)
                contentType(ContentType.Application.Json)
            }

            if (response.status.value in 200..299) {
                Response(data = response.body(), statusCode = response.status.value)
            } else {
                Response(data = null, statusCode = response.status.value, errorBody = response.bodyAsText())
            }
        } catch (e: Exception) {
            Response(data = null, statusCode = -1, errorBody = e.message)
        }
    }
}
