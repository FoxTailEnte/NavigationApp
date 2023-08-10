package com.example.navigationapp.presentation.ui.fragments.yandex

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class YandexViewModelFactory: ViewModelProvider.Factory {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return YandexViewModel() as T
    }
}