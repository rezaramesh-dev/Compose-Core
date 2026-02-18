package com.onestackdev.core.common.base.exception

import java.io.IOException

class NoInternetException(
    message: String = "No internet connection"
) : IOException(message)