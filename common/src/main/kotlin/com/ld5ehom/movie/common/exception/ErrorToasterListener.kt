package com.ld5ehom.movie.common.exception

fun interface ErrorToasterListener {
    fun showErrorToast(exceptionType: ExceptionType)
}
