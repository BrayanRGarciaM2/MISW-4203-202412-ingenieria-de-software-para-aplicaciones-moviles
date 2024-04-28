package com.tsdc.vinilos.album.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Song(
    val id: Int,
    val name: String,
    val duration: String
) : Parcelable
