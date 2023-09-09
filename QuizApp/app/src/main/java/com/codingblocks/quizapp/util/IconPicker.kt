package com.codingblocks.quizapp.util

import com.codingblocks.quizapp.R

object IconPicker {
    private val icons = arrayOf(
        R.drawable.ic_icon1_foreground
    )
    var currentIcon = 0
    fun getIcon():Int {
        return icons[currentIcon]
    }
}

