package com.learning.geoquiz

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.learning.geoquiz.databinding.ActivityShowAnswerBinding
import com.learning.geoquiz.model.Question

class ShowAnswerActivity : AppCompatActivity() {

    lateinit var binding: ActivityShowAnswerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowAnswerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val question = intent.extras?.get("question") as Question
        binding.question.text = question.content
        binding.btnShow.setOnClickListener {
            binding.answer.text = question.answer.toString()
            val intent = Intent()
            intent.putExtra("shown", true)
            setResult(RESULT_OK, intent)
        }
    }
}