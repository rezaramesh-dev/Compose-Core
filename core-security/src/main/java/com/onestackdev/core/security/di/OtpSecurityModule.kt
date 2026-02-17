package com.onestackdev.core.security.di

import com.onestackdev.core.security.otp.OtpRateLimitConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object OtpSecurityModule {

    @Provides
    @Singleton
    fun provideOtpConfig() = OtpRateLimitConfig(
        maxAttempts = 3,
        windowMillis = 60_000,
        blockDurationMillis = 5 * 60_000
    )
}