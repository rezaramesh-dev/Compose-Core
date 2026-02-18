package com.onestackdev.core.common.base.logger

interface AppLogger {

    fun log(
        level: LogLevel,
        tag: String,
        message: String,
        throwable: Throwable? = null
    )

    fun d(tag: String, message: String) =
        log(LogLevel.DEBUG, tag, message)

    fun i(tag: String, message: String) =
        log(LogLevel.INFO, tag, message)

    fun w(tag: String, message: String, throwable: Throwable? = null) =
        log(LogLevel.WARN, tag, message, throwable)

    fun e(tag: String, message: String, throwable: Throwable? = null) =
        log(LogLevel.ERROR, tag, message, throwable)
}