package com.example.llyoddemoproject.di

import com.example.llyoddemoproject.data.repository.ImageRepositoryImpl
import com.example.llyoddemoproject.domain.repository.ImageRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindNewsRepository(newsRepositoryImpl: ImageRepositoryImpl): ImageRepository
}