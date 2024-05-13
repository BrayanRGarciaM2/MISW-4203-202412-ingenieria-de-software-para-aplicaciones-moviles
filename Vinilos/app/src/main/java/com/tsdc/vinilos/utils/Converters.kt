package com.tsdc.vinilos.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tsdc.vinilos.data.model.Artist
import com.tsdc.vinilos.data.model.Song

class Converters {
    @TypeConverter
    fun fromArtistList(value: String): List<Artist> {
        val listType = object : TypeToken<List<Artist>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromArtistList(list: List<Artist>): String {
        val gson = Gson()
        return gson.toJson(list)
    }

    @TypeConverter
    fun fromSongList(value: String): List<Song> {
        val listType = object : TypeToken<List<Song>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromSongList(list: List<Song>): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}
