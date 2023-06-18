package com.example.composition.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
TODO#1.2
Настройки игры.

maxSumValue - максимальная сумма двух чисел
minCountOfRightAnswers - минимальное кол-во правильных ответов
minPercentOfRightAnswers - минимальный процент правильных ответов
gameTimeInSeconds - общее время для прохождения уровня
 */
/**
TODO#11
Заметна интерфейса Serializable на Parcelable.
Процесс сериализации происходит быстрее.
Serializable - маркерный интерфейс(при его реализации ни каких методов переопределять не нужно).
Parcelable - имеет абстрактные методы. При его реализации нужно переопределить все абстрактные методы,
указав какие ПОЛЯ и в каком ПОРЯДКЕ нужно записывать/считывать.
В Kotlin есть специальный плагин - id 'kotlin-parcelize' - устанавливаем, аннотируем
класс @Parcelize(без пакета android).
 */
@Parcelize
data class GameSettings(
    val maxSumValue: Int,
    val minCountOfRightAnswers: Int,
    val minPercentOfRightAnswers: Int,
    val gameTimeInSeconds: Int
) : Parcelable

//    {
//    constructor(parcel: Parcel) : this(
//        parcel.readInt(),
//        parcel.readInt(),
//        parcel.readInt(),
//        parcel.readInt()
//    ) {
//    }
//
//    override fun writeToParcel(parcel: Parcel, flags: Int) {
//        parcel.writeInt(maxSumValue)
//        parcel.writeInt(minCountOfRightAnswers)
//        parcel.writeInt(minPercentOfRightAnswers)
//        parcel.writeInt(gameTimeInSeconds)
//    }
//
//    override fun describeContents(): Int {
//        return 0
//    }
//
//    companion object CREATOR : Parcelable.Creator<GameSettings> {
//        override fun createFromParcel(parcel: Parcel): GameSettings {
//            return GameSettings(parcel)
//        }
//
//        override fun newArray(size: Int): Array<GameSettings?> {
//            return arrayOfNulls(size)
//        }
//    }
//  }