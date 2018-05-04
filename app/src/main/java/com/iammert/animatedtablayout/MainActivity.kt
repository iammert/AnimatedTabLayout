package com.iammert.animatedtablayout

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.util.Log
import com.iammert.library.AnimatedTabLayout

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewpager = findViewById<ViewPager>(R.id.viewpager)
        val atl = findViewById<AnimatedTabLayout>(R.id.atl)

        val adapter = PagerAdapter(supportFragmentManager)
        viewpager.adapter = adapter
        atl.setupViewPager(viewpager)
        atl.setTabChangeListener(object : AnimatedTabLayout.OnChangeListener {
            override fun onChanged(position: Int) {
            }
        })
    }
}
