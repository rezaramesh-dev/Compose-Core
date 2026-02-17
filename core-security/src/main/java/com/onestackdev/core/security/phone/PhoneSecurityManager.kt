package com.onestackdev.core.security.phone

import com.google.i18n.phonenumbers.PhoneNumberUtil
import javax.inject.Inject

class PhoneSecurityManager @Inject constructor(
    private val phoneUtil: PhoneNumberUtil,
) {

    fun normalizeToE164(phone: String, region: String): String? {
        return try {
            val number = phoneUtil.parse(phone, region)
            if (phoneUtil.isValidNumber(number)) {
                phoneUtil.format(
                    number,
                    PhoneNumberUtil.PhoneNumberFormat.E164
                )
            } else null
        } catch (e: Exception) {
            null
        }
    }

    fun isValid(phone: String, region: String): Boolean {
        return try {
            val number = phoneUtil.parse(phone, region)
            phoneUtil.isValidNumber(number)
        } catch (e: Exception) {
            false
        }
    }

    fun getRegion(phone: String): String? {
        return try {
            val number = phoneUtil.parse(phone, null)
            phoneUtil.getRegionCodeForNumber(number)
        } catch (e: Exception) {
            null
        }
    }
}