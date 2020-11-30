package com.learning.geoquiz.ui.question

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.learning.geoquiz.databinding.QuestionFragmentBinding
import com.learning.geoquiz.ui.answer.AnswerFragment

class QuestionFragment : Fragment() {

    private lateinit var binding: QuestionFragmentBinding
    private lateinit var viewModel: QuestionViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = QuestionFragmentBinding.inflate(inflater)

        binding.lifecycleOwner = this

        viewModel = ViewModelProvider(this).get(QuestionViewModel::class.java)

        binding.quetionViewModel = viewModel

        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<Boolean>(
            AnswerFragment.KEY
        )?.observe(viewLifecycleOwner, {
            Log.i("QuestionFragment", it.toString())
            if (it) {
                viewModel.onCheatedCurrentQuestion()
            }
        })

        viewModel.showAnswer.observe(viewLifecycleOwner, {
            if (it) {
                onShowAnswerBtn()
            }
        })

        viewModel.finish.observe(viewLifecycleOwner, {
            if (it) {
                onFinish()
            }
        })
        return binding.root
    }

    private fun onShowAnswerBtn() {
        val question = viewModel.currentQuestion.value
        if (question != null) {
            val action = QuestionFragmentDirections
                .actionQuestionFragmentToAnswerFragment(question)
            Log.i("QuestionFragment", action.toString())
            NavHostFragment.findNavController(this).navigate(action)
        }
    }

    private fun onFinish() {
        val questionaryResult = viewModel.questionaryResult
        val action = QuestionFragmentDirections
            .actionQuestionFragmentToScoreFragment(questionaryResult)
        Log.i("QuestionFragment", action.toString())
        NavHostFragment.findNavController(this).navigate(action)
    }


}