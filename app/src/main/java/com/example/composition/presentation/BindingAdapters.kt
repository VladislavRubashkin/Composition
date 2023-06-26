package com.example.composition.presentation

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.composition.R
import com.example.composition.domain.entity.GameResult

/**
TODO#29
Вся логика установки значений в поля переходит в BindingAdapter.
- Создаётся функция, принимающая тип элемента в котором она будет устанавливать значение(напр ImageView, TextView и пр)
и переменную-значение
- внутри функции производится напр форматирование значения
- далее эта функция вызывается как атрибут у элемента в xml-файле
 */

@BindingAdapter("resultEmoji")
fun bindResultEmoji(imageView: ImageView, winner: Boolean) {
    imageView.setImageResource(getImageResource(winner))
}

/**
TODO#16.1
Устанавливаем картинку в emojiResult, в зависимости от значения поля winner.
 */
private fun getImageResource(winner: Boolean): Int {
    return if (winner) {
        R.drawable.ic_smile
    } else {
        R.drawable.ic_sad
    }
}

@BindingAdapter("requiredAnswers")
fun bindRequiredAnswers(textView: TextView, count: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.required_score),
        count
    )
}

@BindingAdapter("scoreAnswers")
fun bindScoreAnswers(textView: TextView, count: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.score_answers),
        count
    )
}

@BindingAdapter("requiredPercentage")
fun bindRequiredPercentage(textView: TextView, count: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.required_percentage),
        count
    )
}

@BindingAdapter("scorePercentage")
fun bindScorePercentage(textView: TextView, gameResult: GameResult) {
    textView.text = String.format(
        textView.context.getString(R.string.score_percentage),
        getPercentageOfRightAnswers(gameResult)
    )
}

/**
TODO#16.2
Высчитываем процент правильных ответов.
 */
private fun getPercentageOfRightAnswers(gameResult: GameResult) = with(gameResult) {
    if (countOfQuestions == 0) {
        0
    }
    (countOfRightAnswers.toDouble() / countOfQuestions * 100).toInt()
}