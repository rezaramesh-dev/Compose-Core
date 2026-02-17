package com.onestackdev.core.network.di

import com.onestackdev.core.network.ssl.Pin
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(SingletonComponent::class)
abstract class OptionalSslModule {

    @Binds
    @IntoSet
    abstract fun bindNoOpPin(): Pin // Default no-op pin (optional)
}