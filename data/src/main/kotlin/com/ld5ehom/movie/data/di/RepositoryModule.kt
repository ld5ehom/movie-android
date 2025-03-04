package com.ld5ehom.movie.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.ld5ehom.movie.data.impl.MovieRepositoryImpl
import com.ld5ehom.movie.domain.repository.MovieRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindMovieRepository(repo: MovieRepositoryImpl): MovieRepository

}
