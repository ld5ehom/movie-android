package com.ld5ehom.movie.common.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.ld5ehom.movie.common.exception.ErrorHandlerImpl
import com.ld5ehom.movie.component.ErrorHandler

import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
internal abstract class CommonModule {

    @Binds
    @Singleton
    abstract fun bindErrorHandler(errorHandler: ErrorHandlerImpl): ErrorHandler

}
