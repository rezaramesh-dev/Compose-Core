package com.onestackdev.core.common.network

import kotlinx.coroutines.flow.StateFlow

interface NetworkMonitor {
    val isConnected: StateFlow<Boolean>
    fun isConnectedNow(): Boolean
}