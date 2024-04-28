package com.tsdc.vinilos.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PerformerPrize(
    val id: Long,
    val premiationDate: String
) : Parcelable
