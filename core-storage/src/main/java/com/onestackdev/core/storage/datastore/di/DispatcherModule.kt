package com.onestackdev.core.storage.datastore.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DispatcherModule {

    @Provides
    @IODispatcherScope
    @Singleton
    fun provideIODispatcher() = Dispatchers.IO

    @Provides
    @MAINDispatcherScope
    @Singleton
    fun provideMAINDispatcher() = Dispatchers.Main
}