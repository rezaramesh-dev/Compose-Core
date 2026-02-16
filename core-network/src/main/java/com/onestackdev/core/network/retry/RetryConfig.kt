package com.onestackdev.core.network.retry

interface RetryConfig {
    val maxRetry: Int
    val retryDelayMillis: Long
}