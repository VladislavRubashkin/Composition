package com.example.composition.presentation

import android.content.res.ColorStateList
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.composition.R
import com.example.composition.databinding.FragmentGameBinding
import com.example.composition.domain.entity.GameResult
import com.example.composition.domain.entity.Level

class GameFragment : Fragment() {

    private var _binding: FragmentGameBinding? = null
    private val binding: FragmentGameBinding
        get() = _binding ?: throw RuntimeException("FragmentGameBinding == null")

    private lateinit var level: Level

    /**
    TODO#20
    Ленивая инициализация ViewModelFactory.
     */
    private val viewModelFactory by lazy {
        GameViewModelFactory(level, requireActivity().application)
    }

    /**
    TODO#14
    Ленивая инициализация ViewModel. Инициализируется в момент первого обращения.
     */
    private val gameViewModel: GameViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[GameViewModel::class.java]
    }

    /**
    TODO#15
    Ленивая инициализация списка из наших textView с вариантами ответов. Инициализируется в момент первого обращения.
     */
    private val tvOptions by lazy {
        mutableListOf<TextView>().apply {
            add(binding.tvOption1)
            add(binding.tvOption2)
            add(binding.tvOption3)
            add(binding.tvOption4)
            add(binding.tvOption5)
            add(binding.tvOption6)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseArgs()
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
        observeViewModel()
        setClickListenersToOptions()

    }

    /**
    TODO#15.1
    Слушатель нажатия для всех TextView с ответами.
     */
    private fun setClickListenersToOptions() {
        for (tvOption in tvOptions) {
            tvOption.setOnClickListener {
                gameViewModel.chooseRightAnswer(tvOption.text.toString().toInt())
            }
        }
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
        gameViewModel.formattedTime.observe(viewLifecycleOwner) {
            binding.tvTimer.text = it
        }
        gameViewModel.question.observe(viewLifecycleOwner) {
            binding.tvSum.text = it.sum.toString()
            binding.tvLeftNumber.text = it.visibleNumber.toString()
            for (i in 0 until tvOptions.size) {
                tvOptions[i].text = it.options[i].toString()
            }
        }
        gameViewModel.percentOfRightAnswers.observe(viewLifecycleOwner) {
            binding.progressBar.setProgress(it, true)
        }
        gameViewModel.enoughCount.observe(viewLifecycleOwner) {
            val color = getColorByState(it)
            binding.tvAnswersProgress.setTextColor(color)
        }
        gameViewModel.enoughPercent.observe(viewLifecycleOwner) {
            val color = getColorByState(it)
            binding.progressBar.progressTintList = ColorStateList.valueOf(color)
        }
        gameViewModel.minPercent.observe(viewLifecycleOwner) {
            binding.progressBar.secondaryProgress = it
        }
        gameViewModel.progressAnswers.observe(viewLifecycleOwner) {
            binding.tvAnswersProgress.text = it
        }
        gameViewModel.gameResult.observe(viewLifecycleOwner) {
            launchGameFinishedFragment(it)
        }
    }

    /**
    TODO#14.2
    Генерируем цвет, в зависимости от состояния, для enoughCount и enoughPercent.
     */
    private fun getColorByState(goodState: Boolean): Int {
        val colorId = if (goodState) {
            android.R.color.holo_green_light
        } else {
            android.R.color.holo_red_light
        }
        return ContextCompat.getColor(requireContext(), colorId)
    }

    /**
    TODO#8
    Переход на GameFinishedFragment с передачей соответствующих параметров.
     */
    private fun launchGameFinishedFragment(gameResult: GameResult) {
        requireActivity()
            .supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_container, GameFinishedFragment.newInstance(gameResult))
            .addToBackStack(null)
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    /**
    TODO#7
    Принимаем параметры(level игры) по ключу из ChooseLevelFragment.
     */
    private fun parseArgs() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requireArguments().getParcelable(KEY_LEVEL, Level::class.java)?.let {
                level = it
            }
        } else {
            requireArguments().getParcelable<Level>(KEY_LEVEL)?.let {
                level = it
            }
        }
    }

    companion object {

        const val NAME = "GameFragment"
        private const val KEY_LEVEL = "level"

        /**
        TODO#6.2
        Создание GameFragment + передача в аргументы параметров.
         */
        fun newInstance(level: Level): GameFragment {
            return GameFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(KEY_LEVEL, level)
                }
            }
        }
    }
}