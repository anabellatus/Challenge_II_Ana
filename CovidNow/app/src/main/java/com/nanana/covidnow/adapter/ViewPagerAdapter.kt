package com.nanana.covidnow.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.nanana.covidnow.fragment.DiscoverFragment
import com.nanana.covidnow.fragment.HomeFragment
import com.nanana.covidnow.fragment.ProfilFragment

class ViewPagerAdapter(fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {

    private val JUMLAH_MENU = 3

    override fun getItemCount(): Int {
        return JUMLAH_MENU
    }

    override fun createFragment(position: Int): Fragment {
        when (position){
            0 -> { return HomeFragment() }
            1 -> { return DiscoverFragment() }
            2 -> { return ProfilFragment() }
            else -> {
                return HomeFragment()
            }
        }
    }
}