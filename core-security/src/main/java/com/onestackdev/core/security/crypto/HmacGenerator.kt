package com.onestackdev.core.security.crypto

import android.util.Base64
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

object HmacGenerator {

    fun generateHmac(data: String, secret: String): String {
        val mac = Mac.getInstance("HmacSHA256")
        val secretKey = SecretKeySpec(secret.toByteArray(), "HmacSHA256")
        mac.init(secretKey)
        val result = mac.doFinal(data.toByteArray())
        return Base64.encodeToString(result, Base64.NO_WRAP)
    }
}