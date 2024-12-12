package com.example.kshitijsih

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val tabLayout= findViewById<TabLayout>(R.id.recom_tab)
        val viewPager= findViewById<ViewPager2>(R.id.home_viewpager)

        val bottomNavigationView= findViewById<BottomNavigationView>(R.id.bottom_navigation)

        bottomNavigationView.setOnItemSelectedListener { item->
            when(item.itemId){
                R.id.emergency ->{
                    startActivity(Intent(this,Emergency::class.java))
                    false
                }

                else -> {
                    false
                }
            }
        }

        val tab_text= ArrayList<String>()
        tab_text.add("Nearest")
        tab_text.add("Wishlist")
        tab_text.add("Recommendations")

        val pagerAdapter= PagerAdapter(this,tab_text)
        viewPager.adapter=pagerAdapter

        TabLayoutMediator(tabLayout,viewPager){tab, position ->
            Log.d("Check_tab_text", "onCreate:"+ tab_text[position])
            tab.text = tab_text[position]
        }.attach()

        Log.d("Check_tab_text", "onCreate: $tab_text")

    }

    fun prepare_pager(viewPager: ViewPager, arrayList: ArrayList<String>) {

    }

    private  class PagerAdapter(fragmentActivity: FragmentActivity, val tab_text:ArrayList<String>) :
        FragmentStateAdapter(fragmentActivity) {
        override fun getItemCount(): Int = tab_text.size

        override fun createFragment(position: Int): Fragment {
            val recommendationFragment= RecommendationFragment()
            val bundle= Bundle()
            bundle.putString("TITLE", tab_text.get(position))
            recommendationFragment.arguments= bundle
            return recommendationFragment
        }
    }
}