package com.tsdc.vinilos.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.tsdc.vinilos.data.local.album.IAlbumDao
import com.tsdc.vinilos.data.local.artista.IArtistDao
import com.tsdc.vinilos.data.model.AlbumEntity
import com.tsdc.vinilos.data.model.ArtistEntity
import com.tsdc.vinilos.utils.Converters

@Database(entities = [AlbumEntity::class, ArtistEntity::class], version = 2, exportSchema = false)
@TypeConverters(Converters::class)
abstract class VynilsDatabase : RoomDatabase() {
    abstract fun albumDao(): IAlbumDao

    abstract fun artistDao(): IArtistDao


    companion object {
        private var INSTANCE: VynilsDatabase? = null

        fun getDatabase(context: Context): VynilsDatabase {
            INSTANCE = INSTANCE ?: Room.databaseBuilder(
                context.applicationContext,
                VynilsDatabase::class.java,
                "vinyls_database"
            )
                .fallbackToDestructiveMigration() // Permitir migraciones destructivas
                .build()

            return INSTANCE as VynilsDatabase
        }
    }
}
