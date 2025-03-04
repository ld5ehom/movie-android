package com.ld5ehom.movie.remote.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.ld5ehom.movie.data.remote.MovieRemoteDataSource
import com.ld5ehom.movie.remote.impl.MovieRemoteDataSourceImpl
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
internal abstract class RemoteDataSourceModule {

    @Binds
    @Singleton
    abstract fun bindMovieRemoteDataSource(source: MovieRemoteDataSourceImpl): MovieRemoteDataSource

}
