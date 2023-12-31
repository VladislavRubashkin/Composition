package com.example.composition.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.composition.R
import com.example.composition.databinding.FragmentWelcomeBinding

class WelcomeFragment : Fragment() {
    /**
    TODO#4
    При работе с viewBinding во фрагментах принято как и во viewModel работать через две переменные(нулабельную,
    с нижним подчёркиванием и не нулабельную).
    В onCreateView() - инициализируем _binding(его значение через геттер сразу передаётся переменной binding)
    В onDestroyView() - присваиваем переменной _binding значение null
     */
    private var _binding: FragmentWelcomeBinding? = null
    private val binding: FragmentWelcomeBinding
        get() = _binding ?: throw RuntimeException("FragmentWelcomeBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWelcomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        launchChooseLevelFragment()
    }

    /**
    TODO#5
    Переход на ChooseLevelFragment при клике на кнопку buttonUnderstand.
     */
    private fun launchChooseLevelFragment() {
        binding.buttonUnderstand.setOnClickListener {
            findNavController().navigate(R.id.action_welcomeFragment_to_chooseLevelFragment)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = WelcomeFragment()
    }
}