package com.onestackdev.core.common.base.repository

import com.onestackdev.core.common.base.exception.NoInternetException
import retrofit2.HttpException
import java.io.IOException

object ErrorMapper {
    fun map(throwable: Throwable): String = when (throwable) {
        is NoInternetException -> "No internet connection"
        is HttpException -> "Server error: ${throwable.code()}"
        is java.net.SocketTimeoutException -> "Request timed out"
        is IOException -> "IO error: ${throwable.message}"
        else -> "Unknown error"
    }
}