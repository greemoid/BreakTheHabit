package com.greemoid.breakthehabit.presentation

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import javax.inject.Inject

class AddictionPagerAdapter @Inject constructor(fragment: MainFragment) :
    FragmentStateAdapter(fragment) {


    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> TimerFragment()
            1 -> HistoryFragment()
            else -> throw IllegalArgumentException()
        }
    }

}
