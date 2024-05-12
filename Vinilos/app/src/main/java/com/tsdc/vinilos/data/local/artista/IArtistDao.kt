package com.tsdc.vinilos.data.local.artista

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tsdc.vinilos.data.model.ArtistEntity

@Dao
interface IArtistDao {

    @Query("Select * from ArtistEntity")
    suspend fun getAllArtists(): List<ArtistEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveArtist(artist: ArtistEntity)
}