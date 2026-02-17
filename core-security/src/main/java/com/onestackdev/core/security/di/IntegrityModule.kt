package com.onestackdev.core.security.di

import android.content.Context
import com.onestackdev.core.security.integrity.AppIntegrityChecker
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object IntegrityModule {

    @Provides
    @Singleton
    fun provideAppIntegrityChecker(@ApplicationContext context: Context): AppIntegrityChecker =
        AppIntegrityChecker(context)
}