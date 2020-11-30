package com.learning.geoquiz.ui.answer

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.learning.geoquiz.databinding.AnswerFragmentBinding


class AnswerFragment : Fragment() {

    private lateinit var binding: AnswerFragmentBinding
    private lateinit var viewModel: AnswerViewModel
    private lateinit var viewModelFactory: AnswerViewModel.Factory

    companion object {
        const val KEY = "CHEATED"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i("AnswerFragment", savedInstanceState.toString())

        binding = AnswerFragmentBinding.inflate(inflater)

        binding.lifecycleOwner = this

        viewModelFactory =
            AnswerViewModel.Factory(AnswerFragmentArgs.fromBundle(requireArguments()).question)

        viewModel = ViewModelProvider(this, viewModelFactory).get(AnswerViewModel::class.java)

        binding.viewModel = viewModel

        viewModel.cheated.observe(viewLifecycleOwner, {
            Log.i("AnswerFragment", it.toString())
            if (it) {
                binding.answer.visibility = View.VISIBLE
                findNavController().previousBackStackEntry?.savedStateHandle?.set(KEY, true)
            }
        })

        return binding.root
    }

}