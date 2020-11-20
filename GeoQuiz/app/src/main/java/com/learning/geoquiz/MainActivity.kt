package com.learning.geoquiz

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.learning.geoquiz.databinding.ActivityMainBinding
import com.learning.geoquiz.model.Question

class MainActivity : AppCompatActivity() {


    private val mainViewModel: MainViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding
    lateinit var btnFalse: Button
    lateinit var btnTrue: Button
    lateinit var btnPrev: ImageButton
    lateinit var btnNext: ImageButton
    lateinit var btnCheat: Button
    lateinit var questionText: TextView

    private val questionObserver = Observer<Question>  {
        questionText.text = it.content
        if (it.guess == null) {
            btnTrue.isEnabled  = true
            btnFalse.isEnabled = true
        } else {
            btnTrue.isEnabled  = false
            btnFalse.isEnabled = false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        btnFalse = binding.btnFalse
        btnTrue  = binding.btnTrue
        btnPrev  = binding.btnPrev
        btnNext  = binding.btnNext
        btnCheat = binding.btnCheat

        btnFalse.setOnClickListener {
            val answer = mainViewModel.checkAnswer(false)
            Toast.makeText(this, answer, Toast.LENGTH_SHORT).show()
        }
        btnTrue.setOnClickListener {
            val answer = mainViewModel.checkAnswer(true)
            Toast.makeText(this, answer, Toast.LENGTH_SHORT).show()
        }
        btnPrev.setOnClickListener {
            mainViewModel.previousQuestion()
        }
        btnNext.setOnClickListener {
            mainViewModel.nextQuestion()
        }
        btnCheat.setOnClickListener {
            val intent = Intent(this, ShowAnswerActivity::class.java)
            intent.putExtra("question", mainViewModel.currentQuestion.value)
            startActivityForResult(intent, 0)
        }

        questionText = binding.questionText
        mainViewModel.currentQuestion.observe(this, questionObserver)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != Activity.RESULT_OK)
            return
        if (requestCode == 0) {
            if (data == null)
                return
            questionText.text = "CHEATEERRRR"
        }
    }
}

