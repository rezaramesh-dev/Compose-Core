package com.onestackdev.core.security.di

import com.onestackdev.core.security.crypto.EncryptionManager
import com.onestackdev.core.security.crypto.KeyStoreManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SecurityModule {

    @Provides
    @Singleton
    fun provideKeyStoreManager() = KeyStoreManager()

    @Provides
    @Singleton
    fun provideEncryptionManager(
        keyStoreManager: KeyStoreManager,
    ) = EncryptionManager(keyStoreManager)
}