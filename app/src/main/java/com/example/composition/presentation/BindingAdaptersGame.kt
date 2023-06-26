package com.example.composition.presentation

import android.content.Context
import android.content.res.ColorStateList
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter

/**
TODO#30

 */

@BindingAdapter("enoughCount")
fun bindEnoughCount(textView: TextView, state: Boolean) {
    val color = getColorByState(textView.context, state)
    textView.setTextColor(color)
}

@BindingAdapter("enoughPercent")
fun bindEnoughPercent(progressBar: ProgressBar, state: Boolean) {
    val color = getColorByState(progressBar.context, state)
    progressBar.progressTintList = ColorStateList.valueOf(color)
}

/**
TODO#14.2
Генерируем цвет, в зависимости от состояния, для enoughCount и enoughPercent.
 */
private fun getColorByState(context: Context, goodState: Boolean): Int {
    val colorId = if (goodState) {
        android.R.color.holo_green_light
    } else {
        android.R.color.holo_red_light
    }
    return ContextCompat.getColor(context, colorId)
}

@BindingAdapter("numberAsText")
fun bindNumberAsText(textView: TextView, number: Int) {
    textView.text = number.toString()
}

@BindingAdapter("onOptionClickListener")
fun bindOnOptionClickListener(textView: TextView, onOptionClickListener: OnOptionClickListener) {
    textView.setOnClickListener {
        onOptionClickListener.onOptionClick(textView.text.toString().toInt())
    }
}

interface OnOptionClickListener {
    fun onOptionClick(option: Int)
}