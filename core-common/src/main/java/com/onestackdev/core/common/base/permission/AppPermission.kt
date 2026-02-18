package com.onestackdev.core.common.base.permission

import android.Manifest
import android.os.Build
import androidx.annotation.RequiresApi

sealed class AppPermission(val value: String) {

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    data object Notification : AppPermission(
        Manifest.permission.POST_NOTIFICATIONS
    )

    data object Camera : AppPermission(
        Manifest.permission.CAMERA
    )

    data object Location : AppPermission(
        Manifest.permission.ACCESS_FINE_LOCATION
    )

    data object Storage : AppPermission(
        Manifest.permission.READ_EXTERNAL_STORAGE
    )
}