package com.greemoid.breakthehabit.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.greemoid.breakthehabit.data.AddictionModel
import com.greemoid.breakthehabit.data.AddictionRepository
import com.greemoid.breakthehabit.data.TimeSharedPrefs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BaseViewModel @Inject constructor(
    private val timeSharedPrefs: TimeSharedPrefs,
    private val repository: AddictionRepository,
) : ViewModel(), MainViewModel {

    private val _time = MutableLiveData("0 days 0 hours 0 minutes")
    val time: LiveData<String> = _time

    private val _milliseconds = MutableLiveData(0L)
    val milliseconds: LiveData<Long> = _milliseconds

    init {
        getTime()
        getList()
    }

    override fun getTime(): Long {
        _milliseconds.value = timeSharedPrefs.getTime()
        return timeSharedPrefs.getTime()
    }

    override fun saveTime(milliseconds: Long) {
        timeSharedPrefs.saveTime(milliseconds)
    }

    override fun getList(): LiveData<List<AddictionModel>> {
        return repository.getList()
    }

    override suspend fun saveToList(addictionModel: AddictionModel) {
        viewModelScope.launch {
            repository.saveToList(addictionModel)
        }
    }

    override fun delete(addictionModel: AddictionModel) {
        viewModelScope.launch {
            repository.delete(addictionModel)
        }
    }

    override fun convert(): LiveData<String> {

        val m = getTime()
        if (m == 0L) {
            _time.value = "0 days 0 hours 0 minutes"
            _milliseconds.value = 0L
        } else {
            val millis = System.currentTimeMillis() - m
            val seconds = millis / 1000
            val minutes = seconds / 60 % 60
            val hours = seconds / 3600 % 24
            val days = seconds / 86400

            _time.value = "$days days $hours hours $minutes minutes"
        }

        return time
    }
}

