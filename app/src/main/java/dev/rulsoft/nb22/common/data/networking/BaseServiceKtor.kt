package dev.rulsoft.nb22.common.data.networking

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.isSuccess

open class BaseServiceKtor(protected val httpClient: HttpClient) {

    protected suspend inline fun <reified T> executeRequest(request: suspend () -> HttpResponse): Response<T> {
        return try {
            val response = request()
            if (response.status.isSuccess()) {
                Response(data = response.body(), statusCode = response.status.value)
            } else {
                Response(data = null, statusCode = response.status.value, errorBody = response.bodyAsText())
            }
        } catch (e: Exception) {
            Response(data = null, statusCode = -1, errorBody = e.message)
        }
    }
}
