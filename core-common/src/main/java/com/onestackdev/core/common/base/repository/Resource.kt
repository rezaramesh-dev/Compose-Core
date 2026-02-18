package com.onestackdev.core.common.base.repository

sealed class Resource<out T> {
    data class Success<T>(val data: T) : Resource<T>()
    data class Error(val throwable: Throwable, val message: String? = null) : Resource<Nothing>()
    object Loading : Resource<Nothing>()
}