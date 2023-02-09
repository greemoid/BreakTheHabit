package com.greemoid.breakthehabit.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.greemoid.breakthehabit.R
import com.greemoid.breakthehabit.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    val binding get() = _binding!!

    private lateinit var viewPager: ViewPager2


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewPager = binding.viewPager
        val adapter = AddictionPagerAdapter(this)
        binding.viewPager.adapter = adapter

        binding.ivTimer.setImageResource(R.drawable.ic_time)
        binding.ivHistory.setImageResource(R.drawable.ic_history_hidden)

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position == 0) {
                    binding.ivTimer.setImageResource(R.drawable.ic_time)
                    binding.ivHistory.setImageResource(R.drawable.ic_history_hidden)
                } else {
                    binding.ivTimer.setImageResource(R.drawable.ic_time_hidden)
                    binding.ivHistory.setImageResource(R.drawable.ic_history)
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}