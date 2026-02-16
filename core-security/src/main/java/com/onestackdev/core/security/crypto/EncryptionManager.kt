package com.onestackdev.core.security.crypto

import android.util.Base64
import javax.crypto.Cipher
import javax.crypto.spec.GCMParameterSpec
import javax.inject.Inject

class EncryptionManager @Inject constructor(
    private val keyStoreManager: KeyStoreManager,
) {

    private val alias = "core_security_key"

    init {
        keyStoreManager.createKeyIfNotExists(alias)
    }

    fun encrypt(plainText: String): String {

        val cipher = Cipher.getInstance("AES/GCM/NoPadding")
        cipher.init(Cipher.ENCRYPT_MODE, keyStoreManager.getSecretKey(alias))

        val iv = cipher.iv
        val encrypted = cipher.doFinal(plainText.toByteArray())

        val combined = iv + encrypted
        return Base64.encodeToString(combined, Base64.NO_WRAP)
    }

    fun decrypt(encryptedText: String): String {

        val decoded = Base64.decode(encryptedText, Base64.NO_WRAP)

        val iv = decoded.copyOfRange(0, 12) // GCM IV = 12 bytes
        val encrypted = decoded.copyOfRange(12, decoded.size)

        val cipher = Cipher.getInstance("AES/GCM/NoPadding")
        val spec = GCMParameterSpec(128, iv)

        cipher.init(
            Cipher.DECRYPT_MODE,
            keyStoreManager.getSecretKey(alias),
            spec
        )

        return String(cipher.doFinal(encrypted))
    }
}