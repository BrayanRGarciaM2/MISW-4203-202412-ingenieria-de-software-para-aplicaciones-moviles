package com.tsdc.vinilos.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun String.toDate(): Date {
    val format = SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH)
    if (this.contains("null")) {
        return Date()
    }
    return format.parse(this) ?: Date()
}