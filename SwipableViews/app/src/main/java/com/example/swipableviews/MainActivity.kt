package com.example.swipableviews

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val images = listOf(
            R.drawable.character_themuppets_kermit_b77a431b,
            R.drawable.character_themuppets_kermit_b77a431b,
            R.drawable.character_themuppets_kermit_b77a431b
        )
        val adapter = ViewPagerAdapter(images)
        val viewPager = findViewById<ViewPager2>(R.id.viewPager2)
        viewPager.adapter= adapter
        val tabLayout= findViewById<TabLayout>(R.id.tabLayout)
        TabLayoutMediator(tabLayout, viewPager){tab, position ->
            tab.text = "Tab ${position+1}"
        }.attach()
        tabLayout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                Toast.makeText(this@MainActivity,"Selected ${tab?.text}",Toast.LENGTH_SHORT).show()
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                Toast.makeText(this@MainActivity,"Reselected ${tab?.text}",Toast.LENGTH_SHORT).show()
            }
        })
    }
}