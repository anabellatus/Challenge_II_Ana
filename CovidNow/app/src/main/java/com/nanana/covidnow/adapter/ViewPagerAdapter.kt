package com.nanana.covidnow.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.nanana.covidnow.fragment.DiscoverFragment
import com.nanana.covidnow.fragment.HomeFragment
import com.nanana.covidnow.fragment.AboutFragment
import com.nanana.covidnow.fragment.MyFriendFragment

class ViewPagerAdapter(fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {

    private val JUMLAH_MENU = 3

    override fun getItemCount(): Int {
        return JUMLAH_MENU
    }

    override fun createFragment(position: Int): Fragment {
        when (position){
            0 -> { return HomeFragment() }
//            1 -> { return MyFriendFragment() }
            1 -> { return DiscoverFragment() }
            2 -> { return AboutFragment() }
            else -> {
                return HomeFragment()
            }
        }
    }
}