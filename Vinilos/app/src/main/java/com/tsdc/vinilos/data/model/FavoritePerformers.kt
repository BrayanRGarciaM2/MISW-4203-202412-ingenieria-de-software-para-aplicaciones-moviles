package com.tsdc.vinilos.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
data class FavoritePerformers(
    val id: Int,
    val name: String,
    val image: String,
    val description: String,
    val birthDate: Date
): Parcelable