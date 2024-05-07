package com.tsdc.vinilos.core

sealed class Output<out T> {
    class Loading<out T> : Output<T>()
    data class Success<out T>(val data: T) : Output<T>()
    data class Failure<out Exception>(val exception: Exception) : Output<Nothing>()
}
