package com.onestackdev.core.network.ssl

import okhttp3.CertificatePinner
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CertificatePinnerFactory @Inject constructor(
    private val pins: Set<@JvmSuppressWildcards Pin>,
) {

    fun create(): CertificatePinner? {
        if (pins.isEmpty()) return null

        val builder = CertificatePinner.Builder()

        pins.forEach { pin ->
            builder.add(pin.host, pin.sha256)
        }

        return builder.build()
    }
}

//USE

/*@Module
@InstallIn(SingletonComponent::class)
object AppSslModule {

    @Provides
    @Singleton
    @IntoSet
    fun providePrimaryPin(): Pin {
        return Pin(
            host = "api.yourdomain.com",
            sha256 = "sha256/W6ph5Mm5Pz8GgiULbPgzG37mj9g="
        )
    }

    @Provides
    @Singleton
    @IntoSet
    fun provideBackupPin(): Pin {
        return Pin(
            host = "api.yourdomain.com",
            sha256 = "sha256/BackupKeyHashHere"
        )
    }
}*/