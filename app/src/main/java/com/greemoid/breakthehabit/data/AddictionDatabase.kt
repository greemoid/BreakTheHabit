package com.greemoid.breakthehabit.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [AddictionModel::class], version = 2)
abstract class AddictionDatabase : RoomDatabase() {
    abstract fun getAddictionDao(): AddictionDao
}