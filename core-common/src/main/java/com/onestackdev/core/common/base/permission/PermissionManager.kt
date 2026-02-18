package com.onestackdev.core.common.base.permission

import android.app.Activity

interface PermissionManager {

    fun getState(permission: AppPermission, activity: Activity? = null): PermissionState

    fun getStates(permissions: List<AppPermission>, activity: Activity? = null): PermissionResult

    fun isGranted(permission: AppPermission): Boolean
}

//USE

/*sealed class PermissionEvent {

    data class Request(
        val permissions: List<AppPermission>
    ) : PermissionEvent()

    data class Result(
        val result: PermissionResult
    ) : PermissionEvent()
}

@HiltViewModel
class PermissionViewModel @Inject constructor(
    private val permissionManager: PermissionManager
) : ViewModel() {

    private val _events = MutableSharedFlow<PermissionEvent>()
    val events = _events.asSharedFlow()

    suspend fun checkPermissions(
        permissions: List<AppPermission>,
        activity: Activity
    ) {
        val result = permissionManager.getStates(permissions, activity)
        _events.emit(PermissionEvent.Result(result))
    }

    suspend fun requestPermissions(
        permissions: List<AppPermission>
    ) {
        _events.emit(PermissionEvent.Request(permissions))
    }
}*/
