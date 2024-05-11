package com.tsdc.vinilos.data.model;

import kotlinx.parcelize.Parcelize;
import android.os.Parcelable;

@Parcelize
data class Collector(
    val id: Int,
    val name: String,
    val telephone: String,
    val email: String,
    val comments: List<Comment>?,
    val favoritePerformers: List<FavoritePerformers>?,
    val collectorAlbums: List<CollectorAlbums>?,
) : Parcelable
