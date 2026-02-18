package com.onestackdev.core.common.di

import com.onestackdev.core.common.base.permission.DefaultPermissionManager
import com.onestackdev.core.common.base.permission.PermissionManager
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class PermissionModule {

    @Binds
    @Singleton
    abstract fun bindPermissionManager(impl: DefaultPermissionManager): PermissionManager
}