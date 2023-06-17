package com.example.composition.domain.entity

/**
TODO#1.3
Результат игры.

winner - победил/проиграл
countOfRightAnswers - кол-во правильных ответов
countOfQuestions - общее кол-во вопросов
gameSettings: GameSettings - настройки игры
 */
data class GameResult(
    val winner: Boolean,
    val countOfRightAnswers: Int,
    val countOfQuestions: Int,
    val gameSettings: GameSettings
)