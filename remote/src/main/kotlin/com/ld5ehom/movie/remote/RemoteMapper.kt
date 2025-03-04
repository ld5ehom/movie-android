package com.ld5ehom.movie.remote

interface RemoteMapper<DataModel> {
    fun toData(): DataModel
}
