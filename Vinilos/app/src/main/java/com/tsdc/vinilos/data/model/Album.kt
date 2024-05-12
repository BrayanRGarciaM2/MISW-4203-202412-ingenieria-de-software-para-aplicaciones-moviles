package com.tsdc.vinilos.data.model


import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.tsdc.vinilos.utils.toDate
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
data class Album(
    val id: Int,
    val name: String,
    val cover: String,
    val releaseDate: Date = Date(),
    val description: String,
    val genre: String,
    val recordLabel: String,
    val performers: List<Artist> = listOf(),
    val tracks: List<Song> = listOf()
) : Parcelable

data class AlbumList(val results: List<Album> = listOf())

@Entity
data class AlbumEntity(
    @PrimaryKey
    val id: Int,
    @ColumnInfo
    val name: String = "",
    @ColumnInfo
    val cover: String = "",
    @ColumnInfo
    val releaseDate: String,
    @ColumnInfo
    val description: String = "",
    @ColumnInfo
    val genre: String = "",
    @ColumnInfo
    val recordLabel: String = "",
    @ColumnInfo
    val performers: List<Artist> = listOf(),
    @ColumnInfo
    val tracks: List<Song> = listOf()
)

fun List<AlbumEntity>.toAlbumList(): AlbumList {
    val albums = mutableListOf<Album>()
    this.forEach {
        albums.add(it.toAlbum())
    }
    return AlbumList(albums)
}

fun AlbumEntity.toAlbum(): Album {
    return Album(
        id = this.id,
        name = this.name,
        cover = this.cover,
        releaseDate = this.releaseDate.toDate(),
        description = this.description,
        genre = this.genre,
        recordLabel = this.recordLabel,
        performers = this.performers,
        tracks = this.tracks
    )
}

fun Album.toAlbumEntity(): AlbumEntity {
    return AlbumEntity(
        id = this.id,
        name = this.name,
        cover = this.cover,
        releaseDate = this.releaseDate.toString(),
        description = this.description,
        genre = this.genre,
        recordLabel = this.recordLabel,
        performers = this.performers,
        tracks = this.tracks
    )
}
