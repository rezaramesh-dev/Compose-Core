package com.onestackdev.core.security.device

import android.os.Build
import java.io.File
import javax.inject.Inject

class EmulatorDetector @Inject constructor() {

    fun isEmulator(): Boolean {
        return (Build.FINGERPRINT.startsWith("generic")
                || Build.FINGERPRINT.lowercase().contains("vbox")
                || Build.MODEL.contains("Emulator")
                || Build.MODEL.contains("Android SDK built for x86")
                || Build.MANUFACTURER.contains("Genymotion")
                || Build.HARDWARE.contains("goldfish")
                || Build.HARDWARE.contains("ranchu")
                || Build.PRODUCT.contains("sdk")
                || Build.PRODUCT.contains("google_sdk")
                || Build.BOARD.lowercase().contains("nox")
                || Build.BOOTLOADER.lowercase().contains("nox")
                || checkQemuFiles())
    }

    private fun checkQemuFiles(): Boolean {
        val paths = arrayOf(
            "/system/lib/libc_malloc_debug_qemu.so",
            "/sys/qemu_trace",
            "/system/bin/qemu-props"
        )
        return paths.any { File(it).exists() }
    }
}