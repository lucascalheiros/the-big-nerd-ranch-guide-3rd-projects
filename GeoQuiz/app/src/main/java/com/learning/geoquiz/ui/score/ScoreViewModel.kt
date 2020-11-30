package com.learning.geoquiz.ui.score

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.learning.geoquiz.model.QuestionaryResult

class ScoreViewModel(questionaryResult: QuestionaryResult) : ViewModel() {
    val textResult =
        "${questionaryResult.rightQuestions}/${questionaryResult.totalQuestions}"

    class Factory(private val questionaryResult: QuestionaryResult) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ScoreViewModel::class.java)) {
                return ScoreViewModel(questionaryResult) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}