package com.onestackdev.core.network.auth

interface TokenProvider {
    fun getToken(): String?
}