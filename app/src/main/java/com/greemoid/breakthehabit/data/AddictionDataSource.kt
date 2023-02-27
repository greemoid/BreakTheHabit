package com.greemoid.breakthehabit.data

import androidx.lifecycle.LiveData

class AddictionDataSource(
    private val dao: AddictionDao,
) : AddictionRepository {
    override fun getList(): LiveData<List<AddictionModel>> {
        return dao.getList()
    }

    override suspend fun saveToList(addictionModel: AddictionModel) {
        dao.save(addictionModel)
    }

    override suspend fun delete(addictionModel: AddictionModel) {
        dao.delete(addictionModel)
    }

}