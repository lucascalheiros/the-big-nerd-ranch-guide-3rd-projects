package com.learning.geoquiz

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.learning.geoquiz.model.Question

val listQuestions = listOf<Question>(
        Question("Is Brazil in Asia?", false, null),
        Question("Is ice always colder than water?", false, null),
        Question("Is the sun a star?", true, null)
)

class MainViewModel : ViewModel() {
    val currentQuestion: MutableLiveData<Question> by lazy {
        MutableLiveData<Question>()
    }
    var currentQuestionNumber = 0
    var pointCounter          = 0
    var answerCounter         = 0

    init {
        currentQuestion.value = listQuestions[currentQuestionNumber]
    }

    fun checkAnswer(option: Boolean): String {
        listQuestions[currentQuestionNumber].guess = option
        var answer = if (currentQuestion.value?.answer == option) {
            pointCounter++
            answerCounter++
            "Right"
        } else {
            answerCounter++
            "Wrong"
        }
        if (answerCounter == listQuestions.size) {
            answer = "${pointCounter}/${answerCounter}"
        }
        nextQuestion()
        return answer
    }

    fun nextQuestion() {
        currentQuestion.value = listQuestions[++currentQuestionNumber % listQuestions.size]
    }

    fun previousQuestion() {
        currentQuestion.value = listQuestions[--currentQuestionNumber % listQuestions.size]
    }


}