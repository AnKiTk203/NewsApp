package com.codingblocks.newsapp

import android.widget.ImageView
import java.io.Serializable

data class Headlines(
    var article: String ="",
    var date : String = "",
    var image: Int,
    var headline: String = ""
):Serializable

