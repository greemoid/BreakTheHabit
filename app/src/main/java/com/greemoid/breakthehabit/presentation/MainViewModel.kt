package com.greemoid.breakthehabit.presentation

import androidx.lifecycle.LiveData

interface MainViewModel {
    fun getTime(): Long
    fun saveTime(milliseconds: Long)
    fun convert(): LiveData<String>
}