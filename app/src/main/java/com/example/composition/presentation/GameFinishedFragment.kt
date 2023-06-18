package com.example.composition.presentation

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.window.OnBackInvokedCallback
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.FragmentManager
import com.example.composition.databinding.FragmentGameFinishedBinding
import com.example.composition.domain.entity.GameResult

class GameFinishedFragment : Fragment() {

    private var _binding: FragmentGameFinishedBinding? = null
    private val binding: FragmentGameFinishedBinding
        get() = _binding ?: throw RuntimeException("FragmentGameFinishedBinding == null")

    private lateinit var gameResult: GameResult

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseArgs()
        Log.d("GameFinishedFragment", "$gameResult")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameFinishedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /**
        TODO#10
        Слушатель клика на кнопку "назад" у Activity. В activity можно переопределить метод onBackPressed(),
        но во фрагментах этого метода НЕТ.
         */
        val callBack = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                retryGame()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callBack)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    /**
    TODO#9
    Принимаем параметры(gameResult) по ключу из GameFragment.
     */
    private fun parseArgs() {
        gameResult = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requireArguments().getSerializable(KEY_GAME_RESULT, GameResult::class.java) as GameResult
        } else {
            requireArguments().getSerializable(KEY_GAME_RESULT) as GameResult
        }
    }

    /**
    TODO#10.1
    При клике на кнопку "назад" - возвращаемся на фрагмент, который находится в стеке перед GameFragment(на
    данный момент это ChooseLevelFragment).
    Вторым параметром передаём POP_BACK_STACK_INCLUSIVE(и этот фрагмент тоже удаляется из backStack).
    При добавлении фрагмента в backStack, указывается его имя.
    Этот метод надо вызывать внутри callBack -  OnBackPressedCallback(){}
     */
    private fun retryGame() {
        requireActivity()
            .supportFragmentManager
            .popBackStack(GameFragment.NAME, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }

    companion object {

        private const val KEY_GAME_RESULT = "game_result"

        /**
        TODO#8.1
        Создание GameFinishedFragment + передача в аргументы параметров.
         */
        fun newInstance(gameResult: GameResult): GameFinishedFragment {
            return GameFinishedFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(KEY_GAME_RESULT, gameResult)
                }
            }
        }
    }
}