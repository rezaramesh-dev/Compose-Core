package com.onestackdev.core.storage.datastore.security

import kotlinx.coroutines.flow.Flow

interface SecureDataStore {

    suspend fun putString(key: String, value: String)

    fun getStringFlow(key: String, default: String? = null): Flow<String?>

    suspend fun remove(key: String)

    suspend fun clear()
}