package com.onestackdev.core.common.di

import com.onestackdev.core.common.base.logger.AppLogger
import com.onestackdev.core.common.base.logger.DefaultAppLogger
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class LoggerModule {

    @Binds
    @Singleton
    abstract fun bindAppLogger(impl: DefaultAppLogger): AppLogger
}