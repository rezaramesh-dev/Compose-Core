package com.onestackdev.core.security.phone

import java.security.MessageDigest
import javax.inject.Inject

class PhoneHasher @Inject constructor() {

    fun hashPhone(phoneE164: String): String {
        val digest = MessageDigest.getInstance("SHA-256")
        val hash = digest.digest(phoneE164.toByteArray())
        return hash.joinToString("") { "%02x".format(it) }
    }
}

//USE
//val normalized = phoneSecurityManager.normalizeToE164(phone, "DE")
//val hashed = normalized?.let { phoneHasher.hashPhone(it) }