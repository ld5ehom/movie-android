package com.ld5ehom.movie.common.exception

open class IgnoreException @JvmOverloads constructor(
    message: String? = null,
    cause: Throwable? = null
) : Exception(message, cause)
