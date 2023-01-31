package com.greemoid.breakthehabit.data

import androidx.lifecycle.LiveData
import com.greemoid.breakthehabit.presentation.AddictionUi
import com.greemoid.breakthehabit.presentation.AddictionUiModel

interface AddictionRepository {

    fun getList(): LiveData<List<AddictionModel>>
    suspend fun saveToList(addictionModel: AddictionModel)
    fun delete(addictionModel: AddictionModel)
}