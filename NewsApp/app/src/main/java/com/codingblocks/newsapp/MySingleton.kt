package com.codingblocks.newsapp
import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley

class MySingleton(applicationContext: Context) {
    companion object {
        @Volatile
        private var INSTANCE: MySingleton? = null
        fun getInstance(applicationContext: Context) =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: MySingleton(applicationContext).also {
                    INSTANCE = it
                }
            }
    }
    private val requestQueue: RequestQueue by lazy {
        // applicationContext is key, it keeps you from leaking the
        // Activity or BroadcastReceiver if someone passes one in.
        Volley.newRequestQueue(applicationContext.applicationContext)
    }
    fun <T> addToRequestQueue(req: Request<T>) {
        requestQueue.add(req)
    }
}