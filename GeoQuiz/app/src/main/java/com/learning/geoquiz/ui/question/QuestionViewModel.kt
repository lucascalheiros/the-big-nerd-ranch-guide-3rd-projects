package com.learning.geoquiz.ui.question

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.learning.geoquiz.model.Question
import com.learning.geoquiz.model.QuestionaryResult

val listQuestions = listOf(
    Question("Is Brazil in Asia?", false, null, false),
    Question("Is ice always colder than water?", false, null, false),
    Question("Is the sun a star?", true, null, false)
)

class QuestionViewModel : ViewModel() {

    private val _currentQuestion = MutableLiveData<Question>()
    val currentQuestion: LiveData<Question>
        get() = _currentQuestion

    private val _showAnswer = MutableLiveData<Boolean>()
    val showAnswer: LiveData<Boolean>
        get() = _showAnswer

    private val _blocked = MutableLiveData<Boolean>()
    val blocked: LiveData<Boolean>
        get() = _blocked

    private val _finish = MutableLiveData<Boolean>()
    val finish: LiveData<Boolean>
        get() = _finish

    private val _questionaryResult = QuestionaryResult(listQuestions.size,0, 0, 0)
    val questionaryResult: QuestionaryResult
        get() = _questionaryResult.copy()

    private var currentQuestionNumber = 0
    private var answerCounter         = 0

    init {
        _currentQuestion.value = listQuestions[currentQuestionNumber]
        _showAnswer.value = false
    }

    fun showAnswer() {
        _showAnswer.value = true
        _showAnswer.value = false
    }

    private fun finish() {
        _finish.value = true
        _finish.value = false
    }

    fun checkAnswer(option: Boolean): String {
        listQuestions[currentQuestionNumber].guess = option
        var answer = if (_currentQuestion.value?.answer == option) {
            _questionaryResult.rightQuestions++
            answerCounter++
            "Right"
        } else {
            _questionaryResult.wrongQuestions++
            answerCounter++
            "Wrong"
        }
        if (answerCounter == listQuestions.size) {
            answer = "${_questionaryResult.rightQuestions}/${answerCounter}"
        }
        nextQuestion()
        return answer
    }

    fun onCheatedCurrentQuestion() {
        listQuestions[currentQuestionNumber].cheated = true
        _questionaryResult.cheatedQuestions++
        answerCounter++
        questionUpdateFromCurrentNumber()
    }

    fun nextQuestion() {
        currentQuestionNumber = (1 + currentQuestionNumber) % listQuestions.size
        questionUpdateFromCurrentNumber()
    }

    fun previousQuestion() {
        currentQuestionNumber = (currentQuestionNumber + listQuestions.size - 1) % listQuestions.size
        questionUpdateFromCurrentNumber()
    }

    private fun questionUpdateFromCurrentNumber() {
        _currentQuestion.value = listQuestions[currentQuestionNumber]
        _blocked.value = currentQuestion.value?.guess != null || currentQuestion.value?.cheated!!
        if (answerCounter == listQuestions.size) {
            finish()
        }
    }
}