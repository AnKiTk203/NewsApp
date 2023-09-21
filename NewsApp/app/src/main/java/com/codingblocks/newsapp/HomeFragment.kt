package com.codingblocks.newsapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
    private lateinit var  adapter: Adapter
    private lateinit var  recyclerView: RecyclerView
    private lateinit var  newsList: ArrayList<Headlines>

    private lateinit var imageId : ArrayList<Int>
    private lateinit var headlines: ArrayList<String>
    private lateinit var date: ArrayList<String>
    private lateinit var article: ArrayList<String>

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
        dataInitialization()
        val layoutManager = LinearLayoutManager(context)
        recyclerView = view.findViewById(R.id.rvHeadLines)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        adapter = Adapter(newsList,:: onItemClicked)
        recyclerView.adapter = adapter
    }

    private fun dataInitialization(){

        newsList = arrayListOf()
        imageId = arrayListOf(
            R.drawable.a,
            R.drawable.b,
            R.drawable.c,
            R.drawable.d,
            R.drawable.e,
            R.drawable.f,
            R.drawable.g,
            R.drawable.h,
            R.drawable.i,
            R.drawable.j
        )
        headlines = arrayListOf(
            getString(R.string.head_1),
            getString(R.string.head_2),
            getString(R.string.head_3),
            getString(R.string.head_4),
            getString(R.string.head_5),
            getString(R.string.head_6),
            getString(R.string.head_7),
            getString(R.string.head_8),
            getString(R.string.head_9),
            getString(R.string.head_10)
        )
        article = arrayListOf(
            getString(R.string.news_a),
            getString(R.string.news_b),
            getString(R.string.news_c),
            getString(R.string.news_d),
            getString(R.string.news_e),
            getString(R.string.news_f),
            getString(R.string.news_g),
            getString(R.string.news_h),
            getString(R.string.news_i),
            getString(R.string.news_j)
        )
        date = arrayListOf(
            "01-09-23", "02-09-23", "03-09-23", "04-09-23", "05-09-23", "06-09-23", "07-09-23",
            "08-09-23", "09-09-23", "10-09-23"
        )
        for( i in imageId.indices)
        {
            val news = Headlines(article[i],date[i],imageId[i], headlines[i])
            newsList.add(news)
        }
    }
    private fun onItemClicked(data: Headlines){


        val bundle = Bundle()
        val json = Gson().toJson(data)
        bundle.putString("NEWS", json)
        val articleFragment = ArticleFragment()
        articleFragment.arguments = bundle
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout, articleFragment)
        transaction.addToBackStack(null) // Optional, if you want to add it to the back stack
        transaction.commit()
        Toast.makeText(context,"news - ${data.headline}",Toast.LENGTH_SHORT).show()
    }
}