package com.ld5ehom.movie.ui.extension

import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@BindingAdapter("dateText")
fun TextView.setDateText(date: Date?) {
    date ?: return
    val dateFormat =
        SimpleDateFormat("yyyy.MM.dd", Locale.getDefault())
    text = dateFormat.format(date)
}
