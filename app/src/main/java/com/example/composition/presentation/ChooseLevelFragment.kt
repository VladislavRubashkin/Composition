package com.example.composition.presentation

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.composition.R
import com.example.composition.databinding.FragmentChooseLevelBinding
import com.example.composition.domain.entity.Level

class ChooseLevelFragment : Fragment() {

    private var _binding: FragmentChooseLevelBinding? = null
    private val binding: FragmentChooseLevelBinding
        get() = _binding ?: throw RuntimeException("FragmentChooseLevelBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChooseLevelBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buttonsListener()
    }

    /**
    TODO#6.1
    Переход на GameFragment с передачей соответствующих параметров.
    В графе навигации, в destination, в разделе Arguments - добавляем аргумент(Имя, Тип)
    Кодогенерация создаёт специальный классы.
    С их помощью и происходит переход на другой фрагмент с передачей аргументов.
     */
    private fun launchFragment(level: Level) {
        findNavController().navigate(ChooseLevelFragmentDirections.actionChooseLevelFragmentToGameFragment(level))
    }

    /**
    TODO #6
    При клике на соответствующую кнопку переход на GameFragment с передачей соответствующих параметров.
     */
    private fun buttonsListener() = with(binding) {
        buttonLevelTest.setOnClickListener {
            launchFragment(Level.TEST)
        }
        buttonLevelEasy.setOnClickListener {
            launchFragment(Level.EASY)
        }
        buttonLevelNormal.setOnClickListener {
            launchFragment(Level.NORMAL)
        }
        buttonLevelHard.setOnClickListener {
            launchFragment(Level.HARD)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}