package com.onestackdev.core.storage.datastore.di

import jakarta.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class IODispatcherScope

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class MAINDispatcherScope