package dev.rulsoft.nb22.common.domain

sealed class ApiError

data class HttpError(val code: Int, val body: String) : ApiError()

data class NetworkError(val throwable: Throwable) : ApiError()

data class UnknownApiError(val message: String) : ApiError()
