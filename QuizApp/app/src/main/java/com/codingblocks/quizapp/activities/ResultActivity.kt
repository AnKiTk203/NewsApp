package com.codingblocks.quizapp.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.codingblocks.quizapp.R
import com.codingblocks.quizapp.models.Quiz
import com.google.gson.Gson

class ResultActivity : AppCompatActivity() {

    lateinit var quiz: Quiz
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        setUpViews()
    }

    private fun setUpViews() {
        val quizData = intent.getStringExtra("QUIZ")
        quiz = Gson().fromJson(quizData, Quiz::class.java)

        calculateScore()
        setAnswerView()
    }

    private fun setAnswerView() {

    }

    private fun calculateScore() {
      var score = 0;
        for(entry in quiz.questions.entries
    }
}