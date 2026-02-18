package com.onestackdev.core.common.base.permission

data class PermissionResult(val states: Map<AppPermission, PermissionState>) {

    val allGranted: Boolean
        get() = states.values.all {
            it is PermissionState.Granted || it is PermissionState.NotRequired
        }

    val permanentlyDenied: Boolean
        get() = states.values.any { it is PermissionState.PermanentlyDenied }
}