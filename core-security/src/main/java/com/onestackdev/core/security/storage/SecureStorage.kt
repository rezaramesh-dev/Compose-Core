package com.onestackdev.core.security.storage

interface SecureStorage {

    suspend fun save(key: String, value: String)

    suspend fun get(key: String): String?

    suspend fun remove(key: String)

    suspend fun clear()
}