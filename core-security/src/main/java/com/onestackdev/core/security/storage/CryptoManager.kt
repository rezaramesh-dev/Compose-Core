package com.onestackdev.core.security.storage

interface CryptoManager {

    fun encrypt(plainText: String): String

    fun decrypt(cipherText: String): String

    fun invalidateKey()
}