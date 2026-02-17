package com.onestackdev.core.security.otp

data class OtpRateLimitConfig(
    val maxAttempts: Int = 3,
    val windowMillis: Long = 60_000,      // 1 minute
    val blockDurationMillis: Long = 5 * 60_000 // 5 minutes
)