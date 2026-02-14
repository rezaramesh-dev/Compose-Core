package com.onestackdev.core.network.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.onestackdev.core.network.config.NetworkConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.ConnectionSpec
import okhttp3.OkHttpClient
import okhttp3.TlsVersion
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Collections
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @NetworkUrls
    fun provideBaseUrl() = NetworkConfig().baseUrl

    @Provides
    @Singleton
    fun provideConnectionSpec(): ConnectionSpec = ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
        .tlsVersions(TlsVersion.TLS_1_2)
        .build()

    @Provides
    @Singleton
    fun provideOkHttpClient(spec: ConnectionSpec): OkHttpClient = OkHttpClient.Builder().apply {
        connectTimeout(30, TimeUnit.SECONDS)
        readTimeout(30, TimeUnit.SECONDS)
        writeTimeout(30, TimeUnit.SECONDS)
        connectionSpecs(Collections.singletonList(spec))
    }.build()

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    @Singleton
    fun provideRetrofit(@NetworkUrls url: String, client: OkHttpClient, gson: Gson): Retrofit =
        Retrofit.Builder().baseUrl(url)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

}