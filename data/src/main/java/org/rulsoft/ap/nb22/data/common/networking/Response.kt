package org.rulsoft.ap.nb22.data.common.networking

data class Response<T>(
    val data: T?,
    val statusCode: Int,
    val errorBody: String? = null
) {
    val isSuccessful: Boolean
        get() = statusCode in 200..299
}
