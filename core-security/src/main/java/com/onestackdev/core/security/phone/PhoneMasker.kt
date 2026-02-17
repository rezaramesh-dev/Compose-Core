package com.onestackdev.core.security.phone

class PhoneMasker {

    fun mask(phoneE164: String): String {
        if (phoneE164.length < 6) return phoneE164
        val visibleStart = phoneE164.take(3)
        val visibleEnd = phoneE164.takeLast(2)
        return "$visibleStart******$visibleEnd"
    }
}