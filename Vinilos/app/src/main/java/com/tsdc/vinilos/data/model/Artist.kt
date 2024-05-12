package com.tsdc.vinilos.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.tsdc.vinilos.utils.toDate
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
data class Artist(
    val id: Int,
    var name: String,
    val image: String,
    val description: String,
    val birthDate: Date = Date(),
    val createDate: Date = Date(),
    val performerPrizes: List<PerformerPrize> = listOf()
) : Parcelable

data class ArtistList(val results: List<Artist> = listOf())


@Entity
data class ArtistEntity(
    @PrimaryKey
    val id: Int,
    @ColumnInfo
    val name: String = "",
    @ColumnInfo
    val image: String = "",
    @ColumnInfo
    val description: String = "",
    @ColumnInfo
    val birthDate: String,
    @ColumnInfo
    val createDate: String,
    @ColumnInfo
    val performerPrizes: List<PerformerPrize> = listOf()
)

fun List<ArtistEntity>.toArtistList(): ArtistList {
    val artists = mutableListOf<Artist>()
    this.forEach {
        artists.add(it.toArtist())
    }
    return ArtistList(artists)
}

fun ArtistEntity.toArtist(): Artist {
    return Artist(
        id = this.id,
        name = this.name,
        image = this.image,
        description = this.description,
        birthDate = this.birthDate.toDate(),
        createDate = this.createDate.toDate(),
        performerPrizes = this.performerPrizes,
    )
}

fun Artist.toArtistEntity(): ArtistEntity {
    return ArtistEntity(
        id = this.id,
        name = this.name,
        image = this.image,
        description = this.description,
        birthDate = this.birthDate.toString(),
        createDate = this.createDate.toString(),
        performerPrizes = this.performerPrizes
    )
}
