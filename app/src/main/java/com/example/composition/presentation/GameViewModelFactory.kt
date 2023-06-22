package com.example.composition.presentation

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.composition.domain.entity.Level

/**
TODO#19
Чтобы иметь возможность передавать параметры в конструктор viewModel, её необходимо создать с помощью фабрики.
- передаём параметры в конструктор фабрики
- наследуемся от ViewModelProvider.Factory
- переопределяем метод  override fun <T : ViewModel> create(modelClass: Class<T>): T {}
- в этом методе создаём нашу viewModel, и передаём ей в конструктор параметры из конструктора нашей фабрики,
предварительно проверяем что мы создаём именно нашу viewModel
 */
class GameViewModelFactory(
    private val level: Level,
    private val application: Application
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GameViewModel::class.java)) {
            return GameViewModel(application, level) as T
        }
        throw RuntimeException("Unknown view model class $modelClass")
    }
}