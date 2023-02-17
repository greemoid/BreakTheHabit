package com.greemoid.breakthehabit.presentation

import androidx.lifecycle.LiveData
import com.greemoid.breakthehabit.data.AddictionModel

interface MainViewModel {
    fun getTime(): Long
    fun saveTime(milliseconds: Long)
    fun getList(): LiveData<List<AddictionModel>>
    suspend fun saveToList(addictionModel: AddictionModel)
    fun delete(addictionModel: AddictionModel)
    fun convert(): LiveData<String>
}