package com.greemoid.breakthehabit.presentation

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
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
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class TimerFragment : Fragment() {

    private var _binding: FragmentTimerBinding? = null
    private val binding get() = _binding!!

    private lateinit var builder: AlertDialog.Builder

    private val viewModel: BaseViewModel by viewModels()

    private lateinit var dialog: AlertDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentTimerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        invalidateTime()



        binding.btnStart.setOnClickListener {
            showDialog()
            dialog = builder.create()
            dialog.show()
        }


        var timeString = ""
        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val date = Date()
        val current = formatter.format(date)
        binding.btnStop.setOnClickListener {
            viewModel.saveTime(0L)
            invalidateTime()

            val model = AddictionModel(
                days = timeString,
                date = current,
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
                        timeString = string
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

    private fun showDialog() {
        builder = AlertDialog.Builder(requireContext())

        val inflater = layoutInflater
        val dialogView = inflater.inflate(R.layout.dialog_layout, null)

        builder.apply {

            setView(dialogView)

            val leftButton = dialogView.findViewById<Button>(R.id.button_now)
            leftButton.setOnClickListener {
                viewModel.saveTime(System.currentTimeMillis())
                viewModel.getTime()
                setVisibility(STARTED)
                dialog.dismiss()
            }

            val rightButton = dialogView.findViewById<Button>(R.id.button_earlier)
            rightButton.setOnClickListener {
                val calendar = Calendar.getInstance()
                val year = calendar.get(Calendar.YEAR)
                val month = calendar.get(Calendar.MONTH)
                val day = calendar.get(Calendar.DAY_OF_MONTH)
                val hour = calendar.get(Calendar.HOUR_OF_DAY)
                val minute = calendar.get(Calendar.MINUTE)

                val datePickerDialog = DatePickerDialog(
                    requireContext(),
                    { _, selectedYear, selectedMonth, selectedDay ->
                        val timePickerDialog = TimePickerDialog(
                            requireContext(),
                            { _, selectedHour, selectedMinute ->
                                val selectedCalendar = Calendar.getInstance()
                                selectedCalendar.set(selectedYear,
                                    selectedMonth,
                                    selectedDay,
                                    selectedHour,
                                    selectedMinute)
                                val selectedDateTimeInMillis = selectedCalendar.timeInMillis
                                if (selectedDateTimeInMillis > System.currentTimeMillis()) {
                                    Toast.makeText(requireContext(),
                                        "Choose another date",
                                        Toast.LENGTH_SHORT).show()
                                } else {
                                    viewModel.saveTime(selectedDateTimeInMillis)
                                    viewModel.getTime()
                                    setVisibility(STARTED)
                                }
                            },
                            hour,
                            minute,
                            false
                        )
                        timePickerDialog.show()
                    },
                    year,
                    month,
                    day
                )
                datePickerDialog.show()



                dialog.dismiss()
            }
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