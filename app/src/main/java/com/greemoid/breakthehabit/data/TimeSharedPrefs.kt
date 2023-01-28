package com.greemoid.breakthehabit.data

import android.content.Context

interface TimeSharedPrefs {
    fun saveTime(milliseconds: Long)
    fun getTime(): Long

    class Base(context: Context) : TimeSharedPrefs {
        private val sharedPreferences = context.getSharedPreferences(NAME, Context.MODE_PRIVATE)
        override fun saveTime(milliseconds: Long) {
            sharedPreferences.edit().putLong(KEY, milliseconds).apply()
        }

        override fun getTime(): Long {
            return sharedPreferences.getLong(KEY, DEF_TIME)
        }


        companion object {
            private const val KEY = "time"
            private const val NAME = "name"
            private const val DEF_TIME: Long = 0L
        }
    }
}