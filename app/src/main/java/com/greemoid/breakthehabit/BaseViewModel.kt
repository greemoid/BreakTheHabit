package com.greemoid.breakthehabit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BaseViewModel @Inject constructor(
    private val timeSharedPrefs: TimeSharedPrefs,
) : ViewModel(), MainViewModel {

    private val _time = MutableLiveData("")
    val time: LiveData<String> = _time

    private val _milliseconds = MutableLiveData(0L)
    val milliseconds: LiveData<Long> = _milliseconds

    init {
        getTime()
    }

    override fun getTime(): Long {
        _milliseconds.value = timeSharedPrefs.getTime()
        return timeSharedPrefs.getTime()
    }

    override fun saveTime(milliseconds: Long) {
        timeSharedPrefs.saveTime(milliseconds)
    }

    override fun convert(): LiveData<String> {

        val millis = System.currentTimeMillis() - getTime()
        val seconds = millis / 1000
        val minutes = seconds / 60 % 60
        val hours = seconds / 3600 % 24
        val days = seconds / 86400

        _time.value = "$days days $hours hours $minutes minutes"



        return time

    }
}