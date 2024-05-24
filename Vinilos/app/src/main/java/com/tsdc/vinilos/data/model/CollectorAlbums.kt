package com.tsdc.vinilos.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class  CollectorAlbums(
    val id: Int,
    val price: Int,
    val status: String,
): Parcelable