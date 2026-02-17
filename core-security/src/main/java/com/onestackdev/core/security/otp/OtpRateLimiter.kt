package com.onestackdev.core.security.otp

import java.util.concurrent.ConcurrentHashMap
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OtpRateLimiter @Inject constructor(
    private val config: OtpRateLimitConfig
) {

    private val attemptsMap = ConcurrentHashMap<String, MutableList<Long>>()
    private val blockedMap = ConcurrentHashMap<String, Long>()

    fun check(phoneHash: String): OtpRateLimitResult {

        val now = System.currentTimeMillis()

        // 🔒 Check if currently blocked
        blockedMap[phoneHash]?.let { blockTime ->
            if (now < blockTime) {
                return OtpRateLimitResult.Blocked(
                    retryAfterMillis = blockTime - now
                )
            } else {
                blockedMap.remove(phoneHash)
            }
        }

        val attempts = attemptsMap.getOrPut(phoneHash) { mutableListOf() }

        // 🧹 Remove expired attempts
        attempts.removeIf { it < now - config.windowMillis }

        if (attempts.size >= config.maxAttempts) {

            val blockUntil = now + config.blockDurationMillis
            blockedMap[phoneHash] = blockUntil
            attempts.clear()

            return OtpRateLimitResult.Blocked(
                retryAfterMillis = config.blockDurationMillis
            )
        }

        attempts.add(now)
        return OtpRateLimitResult.Allowed
    }
}

//USE IN REPOSITORY

/*class AuthRepository @Inject constructor(
    private val otpRateLimiter: OtpRateLimiter,
    private val phoneHasher: PhoneHasher
) {

    suspend fun requestOtp(phone: String, region: String) {

        val normalized = phoneSecurityManager
            .normalizeToE164(phone, region)
            ?: throw IllegalArgumentException("Invalid phone")

        val hash = phoneHasher.hashPhone(normalized)

        when (val result = otpRateLimiter.check(hash)) {

            is OtpRateLimitResult.Allowed -> {
                sendOtpToServer(normalized)
            }

            is OtpRateLimitResult.Blocked -> {
                throw IllegalStateException(
                    "Try again in ${result.retryAfterMillis / 1000} seconds"
                )
            }
        }
    }
}*/
