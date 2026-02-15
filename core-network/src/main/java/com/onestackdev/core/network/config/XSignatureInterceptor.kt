package com.onestackdev.core.network.config

import okhttp3.Interceptor
import okhttp3.Response
import java.util.Base64
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec
import javax.inject.Inject
import kotlin.random.Random

class XSignatureInterceptor @Inject constructor(
    private val config: SignatureConfig?,
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        if (config == null) {
            return chain.proceed(chain.request())
        }

        val originalRequest = chain.request()

        val xSignature = generateXSignature(
            config.apiKey,
            config.hmacKey
        )

        val newRequest = originalRequest.newBuilder()
            .addHeader("x-signature", xSignature)
            .build()

        return chain.proceed(newRequest)
    }

    fun generateXSignature(publicKey: String, secretKey: String): String {
        val paddedSecretKey = secretKey.padEnd(32, ' ')
        val keyBytes = paddedSecretKey.toByteArray(Charsets.UTF_8)
        val keySpec = SecretKeySpec(keyBytes, "AES")
        val ivBytes = ByteArray(16)
        Random.nextBytes(ivBytes)
        val ivSpec = IvParameterSpec(ivBytes)
        val timestamp = (System.currentTimeMillis() / 1000).toString()
        val message = "$publicKey#$timestamp"
        val messageBytes = message.toByteArray(Charsets.UTF_8)
        val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec)
        val encryptedBytes = cipher.doFinal(messageBytes)
        val combined = ivBytes + encryptedBytes
        return Base64.getEncoder().encodeToString(combined)
    }
}