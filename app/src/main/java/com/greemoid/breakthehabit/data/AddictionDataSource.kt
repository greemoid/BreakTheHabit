package com.greemoid.breakthehabit.data

class AddictionDataSource(
    private val dao: AddictionDao
) : AddictionRepository{
    override fun getList(): List<AddictionModel> {
        return dao.getList()
    }

    override fun saveToList(addictionModel: AddictionModel) {
        dao.save(addictionModel)
    }

}