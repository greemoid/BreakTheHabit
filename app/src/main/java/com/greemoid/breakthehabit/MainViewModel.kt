package com.greemoid.breakthehabit

import androidx.lifecycle.LiveData

interface MainViewModel {
    fun getTime(): Long
    fun saveTime(milliseconds: Long)
    fun convert(): LiveData<String>
}