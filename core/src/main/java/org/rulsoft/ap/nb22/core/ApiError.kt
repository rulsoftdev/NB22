package org.rulsoft.ap.nb22.core

sealed class ApiError

data class HttpError(val code: Int, val body: String) : ApiError()

data class NetworkError(val throwable: Throwable) : ApiError()

data class UnknownApiError(val message: String) : ApiError()
