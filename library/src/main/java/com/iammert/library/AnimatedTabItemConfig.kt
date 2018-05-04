package com.iammert.library

import android.graphics.drawable.Drawable

data class AnimatedTabItemConfig(
        var drawable: Drawable? = null,
        var activeColor: Int = 0,
        var inactiveColor: Int = 0,
        var size: Float = 0f,
        var space: Int = 0)