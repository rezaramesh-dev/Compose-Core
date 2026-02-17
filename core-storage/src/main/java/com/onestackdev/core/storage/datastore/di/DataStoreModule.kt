package com.onestackdev.core.storage.datastore.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.onestackdev.core.storage.datastore.security.SecureDataStore
import com.onestackdev.core.storage.datastore.security.SecureDataStoreImpl
import com.onestackdev.core.storage.datastore.utils.DataStoreConfig.PREFERENCES_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Provides
    @Singleton
    fun provideNoteDataStorePref(
        @ApplicationContext context: Context,
        @IODispatcherScope dispatcher: CoroutineDispatcher,
    ): DataStore<Preferences> = PreferenceDataStoreFactory.create(
        scope = CoroutineScope(dispatcher + SupervisorJob()),
        corruptionHandler = ReplaceFileCorruptionHandler(
            produceNewData = { emptyPreferences() }
        ), produceFile = {
            context.preferencesDataStoreFile(PREFERENCES_NAME)
        }
    )

    @Provides
    @Singleton
    fun provideSecureDataStore(impl: SecureDataStoreImpl): SecureDataStore = impl
}

