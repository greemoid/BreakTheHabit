package com.greemoid.breakthehabit.data


interface TimeSharedPrefs {
    fun saveTime(milliseconds: Long)
    fun getTime(): Long
}