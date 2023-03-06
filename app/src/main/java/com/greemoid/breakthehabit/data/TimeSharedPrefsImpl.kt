package com.greemoid.breakthehabit.data

import android.content.Context

class TimeSharedPrefsImpl(context: Context) : TimeSharedPrefs {



    private val sharedPreferences = context.getSharedPreferences("name", Context.MODE_PRIVATE)
    override fun saveTime(milliseconds: Long) {
        sharedPreferences.edit().putLong(KEY, milliseconds).apply()
    }

    override fun getTime(): Long {
        return sharedPreferences.getLong(KEY, 0L)
    }


    companion object {
        private const val KEY = "time"
    }
}
