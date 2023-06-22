package com.example.composition.presentation

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.composition.R
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

        setupClickListeners()
        bindViews()
    }

    private fun setupClickListeners() {
        /**
        TODO#10
        Слушатель клика на системную кнопку "назад" у Activity. В activity можно переопределить
        метод onBackPressed(), но во фрагментах этого метода НЕТ.
         */
        val callBack = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                retryGame()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callBack)

        /**
        TODO#12
        Слушатель клика на кнопку buttonRetry
         */
        binding.buttonRetry.setOnClickListener {
            retryGame()
        }
    }

    /**
    TODO#16
    Устанавливаем во все TextView значения из объекта gameResult, пришедшего из GameFragment.
     */
    private fun bindViews() = with(binding) {
        emojiResult.setImageResource(getImageResource())
        tvRequiredAnswers.text = String.format(
            getString(R.string.required_score),
            gameResult.gameSettings.minCountOfRightAnswers)
        tvScoreAnswers.text = String.format(
            getString(R.string.score_answers),
            gameResult.countOfRightAnswers
        )
        tvRequiredPercentage.text = String.format(
            getString(R.string.required_percentage),
            gameResult.gameSettings.minPercentOfRightAnswers
        )
        tvScorePercentage.text = String.format(
            getString(R.string.score_percentage),
            getPercentageOfRightAnswers()
        )
    }

    /**
    TODO#16.1
    Устанавливаем картинку в emojiResult, в зависимости от значения поля winner.
     */
    private fun getImageResource(): Int {
        return if (gameResult.winner) {
            R.drawable.ic_smile
        } else {
            R.drawable.ic_sad
        }
    }

    /**
    TODO#16.2
    Высчитываем процент правильных ответов.
     */
    private fun getPercentageOfRightAnswers() = with(gameResult) {
        if (countOfQuestions == 0) {
            0
        }
        (countOfRightAnswers.toDouble() / countOfQuestions * 100).toInt()
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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requireArguments().getParcelable(KEY_GAME_RESULT, GameResult::class.java)?.let {
                gameResult = it
            }
        } else {
            requireArguments().getParcelable<GameResult>(KEY_GAME_RESULT)?.let {
                gameResult = it
            }
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
                    putParcelable(KEY_GAME_RESULT, gameResult)
                }
            }
        }
    }
}