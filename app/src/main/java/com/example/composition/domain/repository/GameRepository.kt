package com.example.composition.domain.repository

import com.example.composition.domain.entity.GameSettings
import com.example.composition.domain.entity.Level
import com.example.composition.domain.entity.Question

/**
TODO#1.4
Domain-слой не от чего не зависит, он не должен знать с чем он работает(напр. БД, сеть и пр).
Для этих целей используется репозиторий - это просто "черный ящик" который умеет работать с данными.
Domain-слой работает ТОЛЬКО с интерфейсом репозитория.
Этот интерфейс передаётся в конструктор useCase, чтобы иметь возможность реализовывать его методы.
 */
interface GameRepository {

    fun getGameSettings(level: Level): GameSettings

    fun generateQuestion(maxSumValue: Int, countOfOption: Int): Question
}