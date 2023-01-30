package com.greemoid.breakthehabit.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AddictionDao {

    @Query("SELECT * FROM addiction_table")
    fun getList(): List<AddictionModel>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun save(addictionModel: AddictionModel)
}