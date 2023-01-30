package com.greemoid.breakthehabit.di

import com.greemoid.breakthehabit.data.AddictionDao
import com.greemoid.breakthehabit.data.AddictionDataSource
import com.greemoid.breakthehabit.data.AddictionRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    fun provideAddictionRepo(dao: AddictionDao): AddictionRepository =
        AddictionDataSource(dao)
}