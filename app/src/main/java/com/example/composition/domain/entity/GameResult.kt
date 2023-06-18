package com.example.composition.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
TODO #1.3
Результат игры.

winner - победил/проиграл
countOfRightAnswers - кол-во правильных ответов
countOfQuestions - общее кол-во вопросов
gameSettings: GameSettings - настройки игры
 */
@Parcelize
data class GameResult(
    val winner: Boolean,
    val countOfRightAnswers: Int,
    val countOfQuestions: Int,
    val gameSettings: GameSettings
) : Parcelable