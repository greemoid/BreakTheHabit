package com.greemoid.breakthehabit.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.greemoid.breakthehabit.R
import com.greemoid.breakthehabit.data.AddictionModel
import com.greemoid.breakthehabit.databinding.FragmentTimerBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TimerFragment : Fragment() {

    private var _binding: FragmentTimerBinding? = null
    val binding get() = _binding!!

    private val viewModel: BaseViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentTimerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        invalidateTime()
        binding.btnStart.setOnClickListener {
            viewModel.saveTime(System.currentTimeMillis())
            viewModel.getTime()
            setVisibility(STARTED)
        }
        binding.btnStop.setOnClickListener {
            viewModel.saveTime(0L)
            invalidateTime()
            val model = AddictionModel(
                days = "10",
                date = "10101",
                image = "sfsdfsd"
            )
            viewModel.viewModelScope.launch {
                viewModel.saveToList(model)
            }
            setVisibility(STOPPED)
        }

        viewModel.milliseconds.observe(viewLifecycleOwner) { time ->
            if (time == 0L) {
                setVisibility(STOPPED)
            } else {
                setVisibility(STARTED)
                lifecycleScope.launchWhenResumed {
                    delay(500)
                    viewModel.convert().observe(viewLifecycleOwner) { string ->
                        binding.tvCounter.text = string
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
            invalidateTime()
        }
    }

    private fun invalidateTime() {
        binding.tvCounter.text = resources.getString(R.string.time, 0, 0, 0)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val STARTED = 1
        const val STOPPED = 0
    }
}