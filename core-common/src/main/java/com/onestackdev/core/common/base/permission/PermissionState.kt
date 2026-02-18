package com.onestackdev.core.common.base.permission

sealed class PermissionState {
    data object Granted : PermissionState()
    data object Denied : PermissionState()
    data object PermanentlyDenied : PermissionState()
    data object NotRequired : PermissionState()
}