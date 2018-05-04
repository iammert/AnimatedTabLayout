package com.iammert.library

import android.content.Context
import android.os.Build
import android.os.Handler
import android.support.annotation.RequiresApi
import android.support.annotation.StyleRes
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.LinearLayout

class AnimatedTabLayout : FrameLayout {

    interface OnChangeListener{
        fun onChanged(position: Int)
    }

    lateinit var containerLinearLayout: LinearLayout

    lateinit var tabs: List<AnimatedTabItemContainer>

    lateinit var selectedTab: AnimatedTabItemContainer

    lateinit var viewPager: ViewPager

    private var onChangeListener: OnChangeListener? = null

    constructor(context: Context) : super(context) {
        init(context, null, 0, 0)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context, attrs, 0, 0)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context, attrs, defStyleAttr, 0)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {
        init(context, attrs, defStyleAttr, defStyleRes)
    }


    private fun init(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) {
        val typedArray = context?.theme?.obtainStyledAttributes(attrs, R.styleable.AnimatedTabLayout, defStyleAttr, defStyleRes)
        val tabXmlResource = typedArray?.getResourceId(R.styleable.AnimatedTabLayout_atl_tabs, 0)

        tabs = AnimatedTabResourceParser(context, tabXmlResource!!).parse()

        val layoutInflater = LayoutInflater.from(getContext())
        val parentView = layoutInflater.inflate(R.layout.view_tablayout_container, this, true)
        containerLinearLayout = parentView.findViewById(R.id.linear_layout_container)

        tabs.forEach { tab ->
            containerLinearLayout.addView(tab)
            tab.setOnClickListener {
                val selectedIndex = tabs.indexOf(tab)
                onPageChangeListener.onPageSelected(selectedIndex)
                viewPager.currentItem = selectedIndex
            }
        }
    }

    fun setTabChangeListener(onChangeListener: OnChangeListener?){
        this.onChangeListener = onChangeListener
    }

    fun setupViewPager(viewPager: ViewPager) {
        this.viewPager = viewPager
        this.viewPager.addOnPageChangeListener(onPageChangeListener)
        selectedTab = tabs[viewPager.currentItem]
        selectedTab.expand()
    }

    private var onPageChangeListener: ViewPager.OnPageChangeListener = object : ViewPager.SimpleOnPageChangeListener() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            if (tabs[position] == selectedTab) {
                return
            }
            selectedTab.collapse()
            selectedTab = tabs[position]
            selectedTab.expand()

            this@AnimatedTabLayout.onChangeListener?.onChanged(position)
        }
    }

}

