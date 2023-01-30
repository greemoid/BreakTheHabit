package com.greemoid.breakthehabit.presentation

import androidx.lifecycle.LiveData
import com.greemoid.breakthehabit.data.AddictionModel

interface MainViewModel {
//    fun getTime(): Long
    fun saveTime(milliseconds: Long)
    fun getList(): LiveData<List<AddictionModel>>
    fun saveToList(addictionModel: AddictionModel)
    fun convert(): LiveData<String>
}