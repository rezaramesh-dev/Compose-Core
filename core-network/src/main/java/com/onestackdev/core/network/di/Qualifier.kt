package com.onestackdev.core.network.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class NetworkUrls

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RetrofitProvide

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ConnectionSpecProvide

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class OkHttpClientProvide

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class GsonProvide


