package com.onestackdev.core.security.otp

sealed class OtpRateLimitResult {

    object Allowed : OtpRateLimitResult()

    data class Blocked(
        val retryAfterMillis: Long
    ) : OtpRateLimitResult()
}