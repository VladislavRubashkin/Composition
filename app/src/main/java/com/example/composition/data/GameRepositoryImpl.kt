package com.example.composition.data

import com.example.composition.domain.entity.GameSettings
import com.example.composition.domain.entity.Level
import com.example.composition.domain.entity.Question
import com.example.composition.domain.repository.GameRepository
import kotlin.math.max
import kotlin.math.min
import kotlin.random.Random

/**
TODO#3
Data-слой - работа с данными

Data-слой предоставляет конкретную реализацию репозиторию.
Data-слой зависит от domain-слоя и знает о нём всё, НО НЕ НАОБОРОТ

Класс делается object(так как он Singleton) - это нужно чтобы не получилось что на одном экране мы работаем с одним
репозиторием, а на другом экране с другим репозиторием.
 */
object GameRepositoryImpl : GameRepository {

    private const val MIN_SUM_VALUE = 2
    private const val MIN_ANSWER_VALUE = 1

    /**
    TODO#3.1
    Генерация настроек игры исходя из выбранного уровня сложности.
    Блок else внутри оператора when() не требуется поскольку мы прописали все возможные варианты. В enum Level
    всего четыре варианта.
     */
    override fun getGameSettings(level: Level): GameSettings {
        return when (level) {
            Level.TEST -> {
                GameSettings(10, 5, 30, 10)
            }
            Level.EASY -> {
                GameSettings(10, 10, 70, 30)
            }
            Level.NORMAL -> {
                GameSettings(100, 15, 80, 60)
            }
            Level.HARD -> {
                GameSettings(300, 20, 90, 60)
            }
        }
    }

    /**
    TODO#3.2
    Генерация вопроса.
     */
    override fun generateQuestion(maxSumValue: Int, countOfOption: Int): Question {
        val sum = Random.nextInt(MIN_SUM_VALUE, maxSumValue + 1)
        val visibleNumber = Random.nextInt(MIN_ANSWER_VALUE, sum)
        val rightAnswer = sum - visibleNumber
        val option = HashSet<Int>()
        option.add(rightAnswer)
        val from = max(rightAnswer - countOfOption, MIN_ANSWER_VALUE)
        val to = min(maxSumValue, rightAnswer + countOfOption)
        while (option.size < countOfOption) {
            option.add(Random.nextInt(from, to))
        }
//        option = option.shuffled().toHashSet()
        return Question(sum, visibleNumber, option.toList())
    }
}