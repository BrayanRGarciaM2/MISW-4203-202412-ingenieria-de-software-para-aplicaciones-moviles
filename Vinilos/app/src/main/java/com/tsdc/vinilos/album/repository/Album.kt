package com.tsdc.vinilos.album.repository


import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
data class Album(
    val id: Int,
    val name: String,
    val cover: String,
    val releaseDate: Date,
    val description: String,
    val genre: String,
    val recordLabel: String,
    val performers: List<Artist>
) : Parcelable
