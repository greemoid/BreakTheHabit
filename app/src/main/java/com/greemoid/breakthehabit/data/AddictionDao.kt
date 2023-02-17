package com.greemoid.breakthehabit.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface AddictionDao {

    @Query("SELECT * FROM addiction_table")
    fun getList(): LiveData<List<AddictionModel>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun save(addictionModel: AddictionModel)

    @Delete
    suspend fun delete(addictionModel: AddictionModel)
}