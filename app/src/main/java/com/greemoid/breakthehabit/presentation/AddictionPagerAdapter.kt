package com.greemoid.breakthehabit.presentation

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import javax.inject.Inject

/*
class AddictionPagerAdapter @Inject constructor(private val context: Context) : PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val layoutInflater = LayoutInflater.from(context)
        val view: View = if (position == 0) {
            layoutInflater.inflate(R.layout.timer_layout, container, false)
        } else {
            layoutInflater.inflate(R.layout.history_layout, container, false)
        }
        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        return 2
    }

}*/

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
