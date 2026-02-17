package com.onestackdev.core.storage.datastore.security

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.onestackdev.core.security.crypto.CryptoException
import com.onestackdev.core.security.storage.CryptoManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SecureDataStoreImpl @Inject constructor(
    private val appDataStore: DataStore<Preferences>,
    private val cryptoManager: CryptoManager,
) : SecureDataStore {

    // ---------------- String ----------------
    override suspend fun putString(key: String, value: String) {
        val encrypted = cryptoManager.encrypt(value)
        val prefKey = stringPreferencesKey(key)
        appDataStore.edit { it[prefKey] = encrypted }
    }

    override fun getStringFlow(key: String, default: String?): Flow<String?> {
        val prefKey = stringPreferencesKey(key)
        return appDataStore.data.map { prefs ->
            val encrypted = prefs[prefKey] ?: return@map default
            try {
                cryptoManager.decrypt(encrypted)
            } catch (_: CryptoException) {
                default
            }
        }
    }

    // ---------------- Remove / Clear ----------------
    override suspend fun remove(key: String) {
        val prefKey = stringPreferencesKey(key)
        appDataStore.edit { it.remove(prefKey) }
    }

    override suspend fun clear() {
        appDataStore.edit { it.clear() }
    }
}