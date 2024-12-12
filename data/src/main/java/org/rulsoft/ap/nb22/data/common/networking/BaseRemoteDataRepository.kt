package org.rulsoft.ap.nb22.data.common.networking

import arrow.core.Either
import org.rulsoft.ap.nb22.core.ApiError
import org.rulsoft.ap.nb22.core.HttpError

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
            Either.Left(
                HttpError(
                    response.statusCode,
                    response.errorBody ?: "Unknown error"
                )
            )
        }
    }
}
