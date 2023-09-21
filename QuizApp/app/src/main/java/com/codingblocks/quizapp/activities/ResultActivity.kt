package com.codingblocks.quizapp.activities

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.codingblocks.quizapp.R
import com.codingblocks.quizapp.models.Quiz
import com.google.gson.Gson
import java.lang.StringBuilder

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
        val btnHome = findViewById<Button>(R.id.btnHome)
        btnHome.setOnClickListener{
            val it = Intent(this,MainActivity::class.java)
            finish()
            startActivity(it)
        }
    }

    private fun setAnswerView() {
        val txtAnswer = findViewById<TextView>(R.id.txtAnswer)
        val builder = StringBuilder("")
        for (entry in quiz.questions.entries){
            val question = entry.value
            builder.append("<font color '#18206F'><b><Question: ${question.description} </b></font><br/><br/>")
        }
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            txtAnswer.text = Html.fromHtml(builder.toString(),Html.FROM_HTML_MODE_COMPACT)
        }
        else {
            txtAnswer.text = Html.fromHtml(builder.toString())
        }
    }

    private fun calculateScore() {
      var score = 0;
        for(entry in quiz.questions.entries)
        {
            val question = entry.value
            if(question.answer == question.userAnswer)
            {
                score+=10
            }
        }
        val txtScore = findViewById<TextView>(R.id.txtScore)
        txtScore.text = "Your Score: $score"
    }
}