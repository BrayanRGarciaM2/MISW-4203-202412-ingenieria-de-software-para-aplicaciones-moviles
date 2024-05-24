package com.tsdc.vinilos.repository.collector.detail

import com.tsdc.vinilos.data.model.Album

interface CollectorDetailRepository {
    suspend fun getAlbumById(albumId: Int): Album?
}