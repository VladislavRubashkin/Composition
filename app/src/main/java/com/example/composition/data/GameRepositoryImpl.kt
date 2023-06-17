package com.example.composition.data

import com.example.composition.domain.entity.GameSettings
import com.example.composition.domain.entity.Level
import com.example.composition.domain.entity.Question
import com.example.composition.domain.repository.GameRepository
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
                GameSettings(10, 3, 30, 10)
            }
            Level.EASY -> {
                GameSettings(10, 10, 70, 60)
            }
            Level.NORMAL -> {
                GameSettings(50, 10, 80, 60)
            }
            Level.HARD -> {
                GameSettings(100, 10, 90, 60)
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
        var option = HashSet<Int>().apply {
            for (i in 0 until countOfOption) {
                if (i == 0) {
                    add(rightAnswer)
                }
                add(Random.nextInt(MIN_ANSWER_VALUE, sum))
            }
        }
        option = option.shuffled().toHashSet()
        return Question(sum, visibleNumber, option.toList())
    }
}