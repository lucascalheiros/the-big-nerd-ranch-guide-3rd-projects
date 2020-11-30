package com.learning.geoquiz.ui.answer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.learning.geoquiz.model.Question

class AnswerViewModel(question: Question) : ViewModel() {
    private val _cheated = MutableLiveData<Boolean>()
    val cheated: LiveData<Boolean>
        get() = _cheated

    private val _question = MutableLiveData(question)
    val question: LiveData<Question>
        get() = _question


    fun onShowAnswer() {
        _cheated.value = true
        _cheated.value = false
    }


    class Factory(private val question: Question) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(AnswerViewModel::class.java)) {
                return AnswerViewModel(question) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

}