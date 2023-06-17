package com.example.composition.domain.entity

/**
TODO#1.2
Настройки игры.

maxSumValue - максимальная сумма двух чисел
minCountOfRightAnswers - минимальное кол-во правильных ответов
minPercentOfRightAnswers - минимальный процент правильных ответов
gameTimeInSeconds - общее время для прохождения уровня
 */
data class GameSettings(
    val maxSumValue: Int,
    val minCountOfRightAnswers: Int,
    val minPercentOfRightAnswers: Int,
    val gameTimeInSeconds: Int
)