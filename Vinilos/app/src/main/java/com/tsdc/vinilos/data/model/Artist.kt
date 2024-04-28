package com.tsdc.vinilos.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
data class Artist(
    val id: Int,
    val name: String,
    val image: String,
    val description: String,
    val birthDate: Date?,
    val createDate: Date?,
    val performerPrizes: List<PerformerPrize>?
) : Parcelable
