package com.example.composition.presentation

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.composition.databinding.FragmentGameBinding
import com.example.composition.domain.entity.GameResult

class GameFragment : Fragment() {

    private var _binding: FragmentGameBinding? = null
    private val binding: FragmentGameBinding
        get() = _binding ?: throw RuntimeException("FragmentGameBinding == null")

    /**
    TODO#26
    Получаем аргумент level из ChooseLevelFragment. Ленивая инициализация.
     */
    private val args by navArgs<GameFragmentArgs>()

    /**
    TODO#20
    Ленивая инициализация ViewModelFactory.
     */
    private val viewModelFactory by lazy {
        GameViewModelFactory(args.level, requireActivity().application)
    }

    /**
    TODO#14
    Ленивая инициализация ViewModel. Инициализируется в момент первого обращения.
     */
    private val gameViewModel: GameViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[GameViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.gameViewModel = gameViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        observeViewModel()

    }

    /**
    TODO#14.1
    Подписываемся на получения и отображения данных для всех элементов.
    - formattedTime - таймер
    - question - получаем сгенерированный вопрос
    - percentOfRightAnswers - процент правильных ответов
    - enoughCount - строка(смена цвета - красный/зелёный) с количеством необходимых правильных ответов и
    фактических правильных ответов
    - enoughPercent - прогресс бар(смена цвета - красный/зелёный)
    - minPercent - прогресс бар(secondary color)
    - progressAnswers - строка с количеством необходимых правильных ответов и фактических правильных
    ответов. Само кол-во.
    - gameResult - по окончании таймера - формируем объект gameResult и отправляем на одноимённый фрагмент
     */
    private fun observeViewModel() {
        gameViewModel.gameResult.observe(viewLifecycleOwner) {
            launchGameFinishedFragment(it)
        }
    }

    /**
    TODO#8
    Переход на GameFinishedFragment с передачей соответствующих параметров.
     */
    private fun launchGameFinishedFragment(gameResult: GameResult) {
        findNavController().navigate(GameFragmentDirections.actionGameFragmentToGameFinishedFragment(gameResult))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}