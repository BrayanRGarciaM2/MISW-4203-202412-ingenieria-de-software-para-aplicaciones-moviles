package com.tsdc.vinilos.repository.artist

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import com.tsdc.vinilos.data.local.album.LocalAlbumDataSource
import com.tsdc.vinilos.data.local.artista.LocalArtistDataSource
import com.tsdc.vinilos.data.model.Album
import com.tsdc.vinilos.data.model.AlbumList
import com.tsdc.vinilos.data.model.Artist
import com.tsdc.vinilos.data.model.ArtistList
import com.tsdc.vinilos.data.remote.artist.ArtistRemoteDataSource

class ArtistRepositoryImpl(
    private val application: Application,
    private val artistRemoteDataSource: ArtistRemoteDataSource,
    private val datasourceLocal: LocalArtistDataSource

): ArtistRepository {
    override suspend fun getArtists(): ArtistList {
        val cachedArtists = datasourceLocal.getArtists()

        if (cachedArtists.results.isEmpty()) {
            val cm =
                application.baseContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if (
                cm.activeNetworkInfo?.type != ConnectivityManager.TYPE_WIFI &&
                cm.activeNetworkInfo?.type != ConnectivityManager.TYPE_MOBILE
            ) {
                return ArtistList()
            } else
                saveArtists(artistRemoteDataSource.getArtists())
        }

            return datasourceLocal.getArtists();
        }

    private suspend fun saveArtists(artist: List<Artist>) {
        datasourceLocal.saveArtists(artist)
    }
}