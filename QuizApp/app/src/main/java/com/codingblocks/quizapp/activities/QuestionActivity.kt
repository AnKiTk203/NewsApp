package com.codingblocks.quizapp.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.codingblocks.quizapp.adapters.OptionAdapter
import com.codingblocks.quizapp.databinding.ActivityQuestionBinding
import com.codingblocks.quizapp.models.Question
import com.codingblocks.quizapp.models.Quiz
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson


class QuestionActivity : AppCompatActivity() {

    var quizzes: MutableList<Quiz>? = null
    var question: MutableMap<String, Question>? = null
    var index = 1


    private lateinit var binding: ActivityQuestionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setUpFireStore()
        setUpEventListener()
    }

    private fun setUpEventListener() {

        binding.btnPrevious.setOnClickListener {
            index--
            bindViews()
        }
        binding.btnNext.setOnClickListener {
            index++
            bindViews()
        }
        binding.btnSubmit.setOnClickListener {
            Log.d("Final quiz", question.toString())
            val intent = Intent(this, ResultActivity::class.java)
            val json = Gson().toJson(quizzes!![0])
            intent.putExtra("QUIZ", json)
            startActivity(intent)
        }
    }

    private fun setUpFireStore() {
        var fireStore = FirebaseFirestore.getInstance()
        var date = intent.getStringExtra("DATE")
        if (date != null) {
            fireStore.collection("quizzes").whereEqualTo("title", date)
                .get()
                .addOnSuccessListener {
                    if (it != null && !it.isEmpty) {
                        quizzes = it.toObjects(Quiz::class.java)
                        question = quizzes!![0].questions
                        bindViews()
                    }
                }
        }

    }

    private fun bindViews() {
        binding.btnNext.visibility = View.GONE
        binding.btnPrevious.visibility = View.GONE
        binding.btnSubmit.visibility = View.GONE

        if (index == 1) {
            binding.btnNext.visibility = View.VISIBLE
        } else if (index == quizzes!!.size) {
            binding.btnNext.visibility = View.VISIBLE
            binding.btnPrevious.visibility = View.VISIBLE
            binding.btnSubmit.visibility = View.VISIBLE
        } else {
            if (question!!.size == 2 && index == 2) {
                binding.btnNext.visibility = View.GONE
                binding.btnSubmit.visibility = View.VISIBLE
                binding.btnPrevious.visibility = View.VISIBLE
            } else {
                binding.btnNext.visibility = View.VISIBLE
                binding.btnPrevious.visibility = View.VISIBLE
            }
        }
        val question = question!!["question$index"]
        question?.let {
            binding.description.text = it.description
            val optionAdapter = OptionAdapter(this, it)
            binding.rvOptions.layoutManager = LinearLayoutManager(this)
            binding.rvOptions.adapter = optionAdapter
        }

    }
}