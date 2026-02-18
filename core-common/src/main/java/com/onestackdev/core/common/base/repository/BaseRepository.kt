package com.onestackdev.core.common.base.repository

import com.onestackdev.core.common.base.exception.NoInternetException
import com.onestackdev.core.common.network.NetworkMonitor
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import java.io.IOException

abstract class BaseRepository(
    protected val networkMonitor: NetworkMonitor,
) {

    // -------------------------
    // Safe API call (suspend) -> Resource<T>
    // -------------------------
    protected suspend fun <T> safeApiCallResource(
        dispatcher: CoroutineDispatcher = IO,
        apiCall: suspend () -> Response<T>,
    ): Resource<T> {
        if (!networkMonitor.isConnectedNow()) return Resource.Error(NoInternetException())

        return try {
            val response = apiCall()
            if (response.isSuccessful) {
                response.body()?.let { Resource.Success(it) }
                    ?: Resource.Error(IOException("Response body is null"))
            } else {
                Resource.Error(IOException("Error code: ${response.code()}"))
            }
        } catch (e: Exception) {
            Resource.Error(e)
        }
    }

    // -------------------------
    // Safe local call -> Resource<T>
    // -------------------------
    protected suspend fun <T> safeCallResource(
        dispatcher: CoroutineDispatcher = IO,
        call: suspend () -> T,
    ): Resource<T> = try {
        Resource.Success(call())
    } catch (e: Exception) {
        Resource.Error(e)
    }

    // -------------------------
    // Flow-based API call -> Resource<T>
    // -------------------------
    protected fun <T> safeApiCallFlow(
        dispatcher: CoroutineDispatcher = IO,
        apiCall: suspend () -> Response<T>,
    ): Flow<Resource<T>> = flow {
        emit(Resource.Loading)
        emit(safeApiCallResource(dispatcher, apiCall))
    }.flowOn(dispatcher)

    // -------------------------
    // Flow-based local call -> Resource<T>
    // -------------------------
    protected fun <T> safeCallFlow(
        dispatcher: CoroutineDispatcher = IO,
        call: suspend () -> T,
    ): Flow<Resource<T>> = flow {
        emit(Resource.Loading)
        emit(safeCallResource(dispatcher, call))
    }.flowOn(dispatcher)

    // -------------------------
    // Retry on network if disconnected (generic)
    // -------------------------
    protected fun <T> retryOnNetwork(apiCall: suspend () -> T): Flow<T> = flow {
        if (!networkMonitor.isConnectedNow()) {
            networkMonitor.isConnected.filter { it }.first()
        }
        emit(apiCall())
    }

    // -------------------------
    // Flow-based API call with retry on network + Resource wrapper
    // -------------------------
    protected fun <T> safeApiCallFlowRetry(
        dispatcher: CoroutineDispatcher = IO,
        retries: Int = 3,
        delayMillis: Long = 1000,
        apiCall: suspend () -> Response<T>
    ): Flow<Resource<T>> = flow {
        repeat(retries) { attempt ->
            if (!networkMonitor.isConnectedNow()) {
                networkMonitor.isConnected.filter { it }.first()
            }
            try {
                emit(safeApiCallResource(dispatcher, apiCall))
                return@flow
            } catch (e: Exception) {
                if (attempt == retries - 1) throw e
                kotlinx.coroutines.delay(delayMillis)
            }
        }
    }.flowOn(dispatcher)
}