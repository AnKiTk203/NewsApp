package com.codingblocks.newsapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.facebook.shimmer.ShimmerFrameLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
    private lateinit var shimmer : ShimmerFrameLayout
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var mAdapter: Adapter
    private lateinit var recyclerView: RecyclerView
    private var newsList: ArrayList<Articles> = ArrayList()
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
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        shimmer = view.findViewById(R.id.shimmer)
        shimmer.startShimmer()
        return view
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
        recyclerView = view.findViewById(R.id.rvHeadLines)
        CoroutineScope(Dispatchers.Main).launch {
                getData()
                delay(2000) // Simulate loading delay
                shimmer.stopShimmer()
                shimmer.visibility = View.GONE
                recyclerView.visibility = View.VISIBLE
        }
        val btn = view.findViewById<FloatingActionButton>(R.id.refreshButton)
        btn.setOnClickListener{
            CoroutineScope(Dispatchers.Main).launch {
                recyclerView.visibility = View.GONE
                shimmer.startShimmer()
                shimmer.visibility = View.VISIBLE
                getData()
                delay(2000) // Simulate loading delay
                shimmer.stopShimmer()
                shimmer.visibility = View.GONE
                recyclerView.visibility = View.VISIBLE
            }
            mAdapter.updateNews(newsList)
        }
    }

    private suspend fun getData() {
        //Creating Instance of my retrofit object
        val news = NewsService.newsInstance.getHeadlines()
        news.enqueue(object: Callback<NewsData>{
            override fun onResponse(call: Call<NewsData>, response: Response<NewsData>) {
                val news = response.body()

                if (news != null) {
                    newsList = news.articles
                }
                if(news!=null) {
                    Log.d("Ankit", news.toString())
                    mAdapter = Adapter(::onItemClicked, this@HomeFragment, newsList)
                        recyclerView.adapter = mAdapter
                        val layoutManager = LinearLayoutManager(context)
                        recyclerView.layoutManager = layoutManager
                }
            }
            override fun onFailure(call: Call<NewsData>, t: Throwable) {
                Log.d("ankit","FAILURE",t)
            }
        })
    }

    private fun onItemClicked(data: Articles) {
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