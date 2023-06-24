package com.example.composition.presentation

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.composition.R
import com.example.composition.databinding.FragmentGameFinishedBinding
import com.example.composition.domain.entity.GameResult

class GameFinishedFragment : Fragment() {

    private var _binding: FragmentGameFinishedBinding? = null
    private val binding: FragmentGameFinishedBinding
        get() = _binding ?: throw RuntimeException("FragmentGameFinishedBinding == null")

    private val args by navArgs<GameFinishedFragmentArgs>()

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
            args.gameResult.gameSettings.minCountOfRightAnswers
        )
        tvScoreAnswers.text = String.format(
            getString(R.string.score_answers),
            args.gameResult.countOfRightAnswers
        )
        tvRequiredPercentage.text = String.format(
            getString(R.string.required_percentage),
            args.gameResult.gameSettings.minPercentOfRightAnswers
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
        return if (args.gameResult.winner) {
            R.drawable.ic_smile
        } else {
            R.drawable.ic_sad
        }
    }

    /**
    TODO#16.2
    Высчитываем процент правильных ответов.
     */
    private fun getPercentageOfRightAnswers() = with(args.gameResult) {
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
    TODO#10.1
    В графе навигации, в action, в разделе popBehavior:
    -  app:popUpTo - фрагмент на который хотим вернуться
    -  app:popUpToInclusive - удалять ли из стека этот фрагмент(на который возвращаемся) или нет
     */
    private fun retryGame() {
        findNavController().popBackStack()
    }

}