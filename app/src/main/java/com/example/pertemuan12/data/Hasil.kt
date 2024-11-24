package com.example.pertemuan12.data

import androidx.core.app.NotificationCompat.MessagingStyle.Message

sealed class Hasil<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T> (data: T?): Hasil<T>(data)
    class Error<T> (data: T? = null, message: String): Hasil<T>(data, message)

}