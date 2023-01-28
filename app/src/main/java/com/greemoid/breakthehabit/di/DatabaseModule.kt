package com.greemoid.breakthehabit.di

import android.content.Context
import androidx.room.Room
import com.greemoid.breakthehabit.data.AddictionDao
import com.greemoid.breakthehabit.data.AddictionDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AddictionDatabase =
        Room.databaseBuilder(context, AddictionDatabase::class.java, "addiction.db")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()

    @Provides
    fun provideAddictionDao(database: AddictionDatabase): AddictionDao =
        database.getAddictionDao()
}