package com.learning.geoquiz.ui.startScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.learning.geoquiz.databinding.StartScreenFragmentBinding

class StartScreenFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = StartScreenFragmentBinding.inflate(inflater)
        binding.startBtn.setOnClickListener {
            onStartBtn()
        }
        return binding.root
    }

    private fun onStartBtn() {
        val action = StartScreenFragmentDirections.actionStartScreenFragmentToQuestionFragment()
        NavHostFragment.findNavController(this).navigate(action)
    }

}