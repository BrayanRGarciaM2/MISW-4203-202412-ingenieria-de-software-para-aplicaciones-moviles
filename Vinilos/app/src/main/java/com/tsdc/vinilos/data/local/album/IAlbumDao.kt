package com.tsdc.vinilos.data.local.album

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tsdc.vinilos.data.model.AlbumEntity

@Dao
interface IAlbumDao {
    @Query("Select * from AlbumEntity")
    suspend fun getAllAlbums(): List<AlbumEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAlbum(album: AlbumEntity)
}
