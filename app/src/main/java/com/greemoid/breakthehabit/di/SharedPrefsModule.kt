package com.greemoid.breakthehabit.di

import android.content.Context
import com.greemoid.breakthehabit.data.TimeSharedPrefs
import com.greemoid.breakthehabit.data.TimeSharedPrefsImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class SharedPrefsModule {

    @Provides
    fun provideTimeSharedPrefs(@ApplicationContext context: Context): TimeSharedPrefs =
        TimeSharedPrefsImpl(context)
}