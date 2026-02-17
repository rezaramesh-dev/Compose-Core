package com.onestackdev.core.security.di

import com.onestackdev.core.security.device.EmulatorDetector
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DeviceModule {

    @Provides
    @Singleton
    fun provideEmulatorDetector(): EmulatorDetector = EmulatorDetector()
}