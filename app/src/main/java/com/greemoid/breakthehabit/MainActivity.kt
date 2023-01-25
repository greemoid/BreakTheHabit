package com.greemoid.breakthehabit

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.greemoid.breakthehabit.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: BaseViewModel by viewModels()
    private val activity = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnStart.setOnClickListener {
            viewModel.saveTime(System.currentTimeMillis())
            setVisibility(STARTED)
        }
        binding.btnStop.setOnClickListener {
            invalidateTime()
            viewModel.saveTime(0L)
            setVisibility(STOPPED)
        }


        viewModel.milliseconds.observe(this) { time ->
            Log.d("timetimetime", time.toString())
            if (time == 0L) {
                setVisibility(STOPPED)
                invalidateTime()
            } else {
                setVisibility(STARTED)
                lifecycleScope.launchWhenResumed {
                    delay(1000)
                    viewModel.convert().observe(activity) {
                        Log.d("timetimetime", it)
                        binding.tvCounter.text = it
                    }
                }
            }
        }

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