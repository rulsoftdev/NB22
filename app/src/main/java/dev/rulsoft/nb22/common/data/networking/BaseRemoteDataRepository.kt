package dev.rulsoft.nb22.common.data.networking

import arrow.core.Either
import dev.rulsoft.nb22.common.domain.ApiError
import dev.rulsoft.nb22.common.domain.HttpError

// Clase base genérica para los repositorios
abstract class BaseRemoteDataRepository {

    protected inline fun <T, R> handleResponse(
        response: Response<T>,
        transform: (T) -> R
    ): Either<ApiError, R> {
        return if (response.isSuccessful) {
            response.data?.let { Either.Right(transform(it)) }
                ?: Either.Left(HttpError(204, "Response body is null"))
        } else {
            Either.Left(HttpError(response.statusCode, response.errorBody ?: "Unknown error"))
        }
    }
}
