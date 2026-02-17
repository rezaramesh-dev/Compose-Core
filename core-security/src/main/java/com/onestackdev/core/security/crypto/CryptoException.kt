package com.onestackdev.core.security.crypto

sealed class CryptoException(message: String) : Exception(message) {

    class EncryptionFailed : CryptoException("Encryption failed")

    class DecryptionFailed : CryptoException("Decryption failed")
}