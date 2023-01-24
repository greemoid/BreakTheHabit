package com.greemoid.breakthehabit

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.greemoid.breakthehabit.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val sharedFrefs = getSharedPreferences("main", Context.MODE_PRIVATE)
        val time = sharedFrefs.getLong("time", 0)
        binding.btnStart.setOnClickListener {
            sharedFrefs.edit().putLong("time", System.currentTimeMillis()).apply()
            setVisibility(STARTED)
        }
        binding.btnStop.setOnClickListener {
            invalidateTime()
            sharedFrefs.edit().putLong("time", 0L).apply()
            setVisibility(STOPPED)
        }

        if (time == 0L) {
            setVisibility(STOPPED)
            invalidateTime()
        } else {
            setVisibility(STARTED)
            convertMilliseconds(System.currentTimeMillis() - time)
        }
    }

    private fun convertMilliseconds(milliseconds: Long) {
        val seconds = milliseconds / 1000
        val minutes = seconds / 60 % 60
        val hours = seconds / 3600 % 24
        val days = seconds / 86400

        binding.tvCounter.text = resources.getString(R.string.time, days, hours, minutes)
    }

    private fun setVisibility(state: Int) {
        if (state == STARTED) {
            binding.btnStart.visibility = View.GONE
            binding.btnStop.visibility = View.VISIBLE
        } else {
            binding.btnStop.visibility = View.GONE
            binding.btnStart.visibility = View.VISIBLE
        }
    }

    private fun invalidateTime() {
        binding.tvCounter.text = resources.getString(R.string.time, 0, 0, 0)
    }

    companion object {
        const val STARTED = 1
        const val STOPPED = 0
    }
}