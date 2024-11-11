package dev.rulsoft.nb22.common.data.networking

data class Response<T>(
    val data: T?,
    val statusCode: Int,
    val errorBody: String? = null
) {
    val isSuccessful: Boolean
        get() = statusCode in 200..299
}
