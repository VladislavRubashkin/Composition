package com.example.composition.domain.usecases

import com.example.composition.domain.entity.Question
import com.example.composition.domain.repository.GameRepository

/**
TODO#1.5
Генерируем вопрос.
 */
class GenerateQuestionUseCase(
    private val gameRepository: GameRepository
) {
    /**
    TODO#2
    Так как UseCase всегда делает что-то одно, можно переопределить оператор invoke. Тогда UseCase можно будет
    вызывать как метод.
     */
    operator fun invoke(maxSumValue: Int): Question {
        return gameRepository.generateQuestion(maxSumValue, COUNT_OF_OPTIONS)
    }

    /**
    TODO#1.6
    Кол-во вариантов ответов.
     */
    private companion object {
        private const val COUNT_OF_OPTIONS = 6
    }
}