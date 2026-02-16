package com.onestackdev.core.network.retry

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class RetryInterceptor @Inject constructor(
    private val retryConfig: RetryConfig?,
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        var attempt = 0
        var lastException: Exception? = null

        while (attempt <= (retryConfig?.maxRetry ?: 0)) {
            try {
                return chain.proceed(chain.request())
            } catch (e: Exception) {
                lastException = e
                if (attempt >= (retryConfig?.maxRetry ?: 0)) {
                    break
                }
                Thread.sleep(retryConfig?.retryDelayMillis ?: 0)
            }
            attempt++
        }
        throw lastException ?: RuntimeException("Unknown network error")
    }
}