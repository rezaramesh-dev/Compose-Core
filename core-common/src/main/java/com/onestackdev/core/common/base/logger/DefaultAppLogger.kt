package com.onestackdev.core.common.base.logger

import android.util.Log
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultAppLogger @Inject constructor() : AppLogger {

    override fun log(
        level: LogLevel,
        tag: String,
        message: String,
        throwable: Throwable?,
    ) {
        when (level) {
            LogLevel.DEBUG -> Log.d(tag, message)
            LogLevel.INFO -> Log.i(tag, message)
            LogLevel.WARN -> Log.w(tag, message, throwable)
            LogLevel.ERROR -> Log.e(tag, message, throwable)
        }
    }
}