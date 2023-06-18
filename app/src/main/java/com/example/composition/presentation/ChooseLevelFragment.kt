package com.example.composition.presentation

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
    Запуск GameFragment.
     */
    private fun launchFragment(level: Level) {
        requireActivity().supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_container, GameFragment.newInstance(level))
            .addToBackStack(GameFragment.NAME)
            .commit()
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

    companion object {

        const val NAME = "ChooseLevelFragment"
        fun newInstance() = ChooseLevelFragment()
    }
}