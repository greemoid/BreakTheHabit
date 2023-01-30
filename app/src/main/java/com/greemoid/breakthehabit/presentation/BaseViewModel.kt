package com.greemoid.breakthehabit.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.greemoid.breakthehabit.data.AddictionModel
import com.greemoid.breakthehabit.data.AddictionRepository
import com.greemoid.breakthehabit.data.TimeSharedPrefs
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BaseViewModel @Inject constructor(
    private val timeSharedPrefs: TimeSharedPrefs,
    private val repository: AddictionRepository,
) : ViewModel(), MainViewModel {

    private val _time = MutableLiveData("")
    val time: LiveData<String> = _time

    private val _milliseconds = MutableLiveData(0L)
    val milliseconds: LiveData<Long> = _milliseconds

    private val _list = MutableLiveData<List<AddictionModel>>()
    private val list: LiveData<List<AddictionModel>> = _list

    /*override fun getTime(): Long {
        _milliseconds.value = timeSharedPrefs.getTime()
        return timeSharedPrefs.getTime()
    }*/

    override fun saveTime(milliseconds: Long) {
        timeSharedPrefs.saveTime(milliseconds)
    }

    override fun getList(): LiveData<List<AddictionModel>> {
        _list.value = repository.getList()
        return list
    }

    override fun saveToList(addictionModel: AddictionModel) {
        repository.saveToList(addictionModel)
    }

    override fun convert(): LiveData<String> {

        val millis = System.currentTimeMillis() - timeSharedPrefs.getTime()
        val seconds = millis / 1000
        val minutes = seconds / 60 % 60
        val hours = seconds / 3600 % 24
        val days = seconds / 86400

        _time.value = "$days days $hours hours $minutes minutes"



        return time

    }
}