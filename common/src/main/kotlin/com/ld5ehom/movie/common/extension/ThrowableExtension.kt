package com.ld5ehom.movie.common.extension

import com.ld5ehom.movie.common.exception.ErrorUtil
import com.ld5ehom.movie.common.util.loge
import java.io.PrintWriter
import java.io.StringWriter

fun Throwable.log() {
    val stringWriter = StringWriter()
    printStackTrace(PrintWriter(stringWriter))
    loge(stringWriter.toString())
}

fun Throwable.logException() {
    ErrorUtil.logException(this)
}

fun Throwable.handleError() {
    ErrorUtil.handleError(this)
}
