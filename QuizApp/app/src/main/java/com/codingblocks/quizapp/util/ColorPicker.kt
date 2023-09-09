package com.codingblocks.quizapp.util

object ColorPicker {
    private val colors = arrayOf(
        "#FF0000", // Red
        "#0000FF", // Blue
        "#000000", // Black
        "#FFA500", // Orange
        "#800080", // Purple
        "#FFC0CB", // Pink
        "#A52A2A"  // Brown
    )
    var currentColor = 0
    fun getColors(): String {
        currentColor = (currentColor + 1) % colors.size
        return colors[currentColor]
    }
}