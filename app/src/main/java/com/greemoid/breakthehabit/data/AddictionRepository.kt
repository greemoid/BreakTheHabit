package com.greemoid.breakthehabit.data

interface AddictionRepository {

    fun getList(): List<AddictionModel>
    fun save(addictionModel: AddictionModel)
}