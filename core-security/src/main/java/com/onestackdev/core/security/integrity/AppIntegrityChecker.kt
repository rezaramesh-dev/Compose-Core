package com.onestackdev.core.security.integrity

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.annotation.RequiresApi
import dagger.hilt.android.qualifiers.ApplicationContext
import jakarta.inject.Singleton
import java.security.MessageDigest
import javax.inject.Inject

@Singleton
class AppIntegrityChecker @Inject constructor(
    @param:ApplicationContext private val context: Context,
) {

    @RequiresApi(Build.VERSION_CODES.P)
    fun isSignatureValid(expectedSha256: String): Boolean {
        return try {
            val packageInfo = context.packageManager.getPackageInfo(
                context.packageName,
                PackageManager.GET_SIGNING_CERTIFICATES
            )

            val signatures = packageInfo.signingInfo!!.apkContentsSigners
            signatures.any { signature ->
                val md = MessageDigest.getInstance("SHA-256")
                val digest = md.digest(signature.toByteArray())
                val sha = digest.joinToString("") { "%02x".format(it) }
                sha.equals(expectedSha256, ignoreCase = true)
            }
        } catch (_: Exception) {
            false
        }
    }
}

//USE

/*class SecurityRepository @Inject constructor(
    private val integrityChecker: AppIntegrityChecker
) {

    fun validateApp(): Boolean {
        val expectedSha256 = "expected_sha256_of_signed_apk"
        return integrityChecker.isSignatureValid(expectedSha256)
    }
}*/
