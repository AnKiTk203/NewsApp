package com.codingblocks.newsapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var mAdapter: Adapter
    private lateinit var recyclerView: RecyclerView
    private var newsList: ArrayList<Headlines> = ArrayList()
    private var retryCount = 0
    private val maxRetry = 3

//    private late init var imageId : ArrayList<Int>
//    private late init var headlines: ArrayList<String>
//    private late init var date: ArrayList<String>
//    private late init var article: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchData()
        val layoutManager = LinearLayoutManager(context)
        recyclerView = view.findViewById(R.id.rvHeadLines)
        recyclerView.layoutManager = layoutManager
        mAdapter = Adapter(::onItemClicked)
        recyclerView.adapter = mAdapter
    }

    private fun fetchData() {

        val queue = Volley.newRequestQueue(context)
        val url = "https://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=8187ba8f2544412eabbcb3716989f8ca"
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null, {
                val jsonArray = it.getJSONArray("articles")
                for (i in 0 until jsonArray.length()) {
                    val newsJsonObject = jsonArray.getJSONObject(i)
                    val news = Headlines(
                        newsJsonObject.getString("content"),
                        newsJsonObject.getString("publishedAt"),
                        newsJsonObject.getString("urlToImage"),
                        newsJsonObject.getString("title")
                    )
                    newsList.add(news)
                }
                mAdapter.updateNews(newsList)
            }
        ) {
            if (retryCount < maxRetry) {
                // Retry the request
                Toast.makeText(context, "Error!", Toast.LENGTH_SHORT).show()
                retryCount++
                fetchData()
            } else {
                // Maximum retry attempts reached, handle the error
                Toast.makeText(
                    context,
                    "Failed to fetch data after $maxRetry retries",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
        queue.add(jsonObjectRequest)
    }

    //    private fun dataInitialization(){
//
//        newsList = arrayListOf()
//        imageId = arrayListOf(
//            R.drawable.a,
//            R.drawable.b,
//            R.drawable.c,
//            R.drawable.d,
//            R.drawable.e,
//            R.drawable.f,
//            R.drawable.g,
//            R.drawable.h,
//            R.drawable.i,
//            R.drawable.j
//        )
//        headlines = arrayListOf(
//            getString(R.string.head_1),
//            getString(R.string.head_2),
//            getString(R.string.head_3),
//            getString(R.string.head_4),
//            getString(R.string.head_5),
//            getString(R.string.head_6),
//            getString(R.string.head_7),
//            getString(R.string.head_8),
//            getString(R.string.head_9),
//            getString(R.string.head_10)
//        )
//        article = arrayListOf(
//            getString(R.string.news_a),
//            getString(R.string.news_b),
//            getString(R.string.news_c),
//            getString(R.string.news_d),
//            getString(R.string.news_e),
//            getString(R.string.news_f),
//            getString(R.string.news_g),
//            getString(R.string.news_h),
//            getString(R.string.news_i),
//            getString(R.string.news_j)
//        )
//        date = arrayListOf(
//            "01-09-23", "02-09-23", "03-09-23", "04-09-23", "05-09-23", "06-09-23", "07-09-23",
//            "08-09-23", "09-09-23", "10-09-23"
//        )
//        for( i in imageId.indices)
//        {
//            val news = Headlines(article[i],date[i],imageId[i], headlines[i])
//            newsList.add(news)
//        }
//    }
    private fun onItemClicked(data: Headlines) {
        val bundle = Bundle()
        val json = Gson().toJson(data)
        bundle.putString("NEWS", json)
        val articleFragment = ArticleFragment()
        articleFragment.arguments = bundle
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout, articleFragment)
        transaction.addToBackStack(null) // Optional, if you want to add it to the back stack
        transaction.commit()
        Toast.makeText(context, "news - ${data.title}", Toast.LENGTH_SHORT).show()
    }
}