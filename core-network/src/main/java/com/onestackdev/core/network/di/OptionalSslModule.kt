package com.onestackdev.core.network.di

import com.onestackdev.core.network.ssl.Pin
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(SingletonComponent::class)
object OptionalSslModule {

    @Provides
    @IntoSet
    fun provideNoOpPin(): Pin {
        return Pin(host = "", sha256 = "")
    }
}