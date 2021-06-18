package com.iammert.library

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.viewpager.widget.ViewPager

class AnimatedTabLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    interface OnChangeListener {
        fun onChanged(position: Int)
    }

    private var containerLinearLayout: LinearLayout

    private var tabs: List<AnimatedTabItemContainer>

    private lateinit var selectedTab: AnimatedTabItemContainer

    private lateinit var viewPager: ViewPager

    private var onChangeListener: OnChangeListener? = null

    private val typedArray = context.theme?.obtainStyledAttributes(
        attrs,
        R.styleable.AnimatedTabLayout,
        defStyleAttr,
        defStyleRes
    )

    init {
        val tabXmlResource = typedArray?.getResourceId(R.styleable.AnimatedTabLayout_atl_tabs, 0)

        tabs = AnimatedTabResourceParser(context, tabXmlResource!!).parse()

        val layoutInflater = LayoutInflater.from(context)
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

    fun setTabChangeListener(onChangeListener: OnChangeListener?) {
        this.onChangeListener = onChangeListener
    }

    fun setupViewPager(viewPager: ViewPager) {
        this.viewPager = viewPager
        this.viewPager.addOnPageChangeListener(onPageChangeListener)
        selectedTab = tabs[viewPager.currentItem]
        selectedTab.expand()
    }

    private var onPageChangeListener: ViewPager.OnPageChangeListener =
        object : ViewPager.SimpleOnPageChangeListener() {
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

