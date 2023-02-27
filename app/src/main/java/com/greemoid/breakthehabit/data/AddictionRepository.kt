package com.greemoid.breakthehabit.data

import androidx.lifecycle.LiveData

interface AddictionRepository {

    fun getList(): LiveData<List<AddictionModel>>
    suspend fun saveToList(addictionModel: AddictionModel)
    suspend fun delete(addictionModel: AddictionModel)
}