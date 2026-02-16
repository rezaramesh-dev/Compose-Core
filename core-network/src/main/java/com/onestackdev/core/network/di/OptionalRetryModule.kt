package com.onestackdev.core.network.di

import com.onestackdev.core.network.retry.RetryConfig
import dagger.BindsOptionalOf
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class OptionalRetryModule {

    @BindsOptionalOf
    abstract fun bindOptionalRetryConfig(): RetryConfig
}