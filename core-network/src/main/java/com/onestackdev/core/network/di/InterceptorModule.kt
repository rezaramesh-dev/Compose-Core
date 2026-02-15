package com.onestackdev.core.network.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor

@Module
@InstallIn(SingletonComponent::class)
abstract class InterceptorModule {

    @Binds
    @IntoSet
    abstract fun bindLoggingInterceptor(
        interceptor: HttpLoggingInterceptor
    ): Interceptor
}