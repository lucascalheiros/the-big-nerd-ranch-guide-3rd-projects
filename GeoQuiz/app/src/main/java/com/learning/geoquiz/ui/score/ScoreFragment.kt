package com.learning.geoquiz.ui.score

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.learning.geoquiz.databinding.ScoreFragmentBinding

class ScoreFragment : Fragment() {

    private lateinit var viewModel: ScoreViewModel
    private lateinit var viewModelFactory: ScoreViewModel.Factory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = ScoreFragmentBinding.inflate(inflater)

        binding.lifecycleOwner = viewLifecycleOwner

        viewModelFactory =
            ScoreViewModel.Factory(ScoreFragmentArgs.fromBundle(requireArguments()).questionaryResult)

        viewModel = ViewModelProvider(this, viewModelFactory).get(ScoreViewModel::class.java)

        binding.viewModel = viewModel

        return binding.root
    }

}