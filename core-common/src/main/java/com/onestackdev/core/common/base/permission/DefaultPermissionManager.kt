package com.onestackdev.core.common.base.permission

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultPermissionManager @Inject constructor(
    @param:ApplicationContext private val context: Context,
) : PermissionManager {

    override fun isGranted(permission: AppPermission): Boolean {

        if (permission is AppPermission.Notification &&
            Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU
        ) return true

        return ContextCompat.checkSelfPermission(context, permission.value) ==
                PackageManager.PERMISSION_GRANTED
    }

    override fun getState(permission: AppPermission, activity: Activity?): PermissionState {

        if (permission is AppPermission.Notification &&
            Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU
        ) return PermissionState.NotRequired

        if (isGranted(permission)) return PermissionState.Granted

        if (activity == null) return PermissionState.Denied

        val showRationale =
            ActivityCompat.shouldShowRequestPermissionRationale(activity, permission.value)

        return if (!showRationale) {
            PermissionState.PermanentlyDenied
        } else {
            PermissionState.Denied
        }
    }

    override fun getStates(
        permissions: List<AppPermission>, activity: Activity?,
    ): PermissionResult {

        val map = permissions.associateWith {
            getState(it, activity)
        }

        return PermissionResult(map)
    }
}