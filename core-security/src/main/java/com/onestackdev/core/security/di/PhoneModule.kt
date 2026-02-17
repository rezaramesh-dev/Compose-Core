package com.onestackdev.core.security.di

import com.google.i18n.phonenumbers.PhoneNumberUtil
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PhoneModule {

    @Provides
    @Singleton
    fun providePhoneNumberUtil(): PhoneNumberUtil =
        PhoneNumberUtil.getInstance()
}