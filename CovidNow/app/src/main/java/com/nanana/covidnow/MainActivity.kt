package com.nanana.covidnow

import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.res.ResourcesCompat
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.material.tabs.TabLayoutMediator.TabConfigurationStrategy
import com.nanana.covidnow.adapter.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val title = arrayOf("Home", "Discover", "About")
    val icon = arrayOf(R.drawable.ic_home_black_fill, R.drawable.ic_search_black, R.drawable.ic_bacteria)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = ViewPagerAdapter(this)
        view_pager.adapter = adapter

        TabLayoutMediator(tab_layout, view_pager, TabConfigurationStrategy { tab, position ->
            tab.text = title[position]
            tab.icon = ResourcesCompat.getDrawable(resources, icon[position], null)
        }).attach()
    }
}
