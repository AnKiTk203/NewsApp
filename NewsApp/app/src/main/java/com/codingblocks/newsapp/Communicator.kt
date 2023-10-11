package com.codingblocks.newsapp

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

//https://newsapi.org/v2/top-headlines?country=in&apiKey=8187ba8f2544412eabbcb3716989f8ca
const val BASEURL = "https://newsapi.org/"
const val APIKEY = "8187ba8f2544412eabbcb3716989f8ca"
interface NewsInterface {
    @GET("v2/top-headlines?country=in&apiKey=$APIKEY")
    fun getHeadlines(): Call<NewsData>
}

object NewsService{
    val newsInstance: NewsInterface
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASEURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        newsInstance = retrofit.create(NewsInterface::class.java)
    }
}







