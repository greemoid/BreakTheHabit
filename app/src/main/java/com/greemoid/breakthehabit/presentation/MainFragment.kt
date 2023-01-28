package com.greemoid.breakthehabit.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.greemoid.breakthehabit.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private val viewModel: BaseViewModel by viewModels()

    private lateinit var viewPager: ViewPager2

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?,
        ): View? {
            binding = FragmentMainBinding.inflate(inflater, container, false)
            return binding.root
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            viewPager = binding.viewPager
            val adapter = AddictionPagerAdapter(this)
            binding.viewPager.adapter = adapter

        }
        /* binding.btnStart.setOnClickListener {
             viewModel.saveTime(System.currentTimeMillis())
             setVisibility(STARTED)
         }
         binding.btnStop.setOnClickListener {
             invalidateTime()
             viewModel.saveTime(0L)
             setVisibility(STOPPED)
         }


         viewModel.milliseconds.observe(viewLifecycleOwner) { time ->
             Log.d("timetimetime", time.toString())
             if (time == 0L) {
                 setVisibility(STOPPED)
                 invalidateTime()
             } else {
                 setVisibility(STARTED)
                 lifecycleScope.launchWhenResumed {
                     delay(100)
                     viewModel.convert().observe(viewLifecycleOwner) {
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
     }*/
    }