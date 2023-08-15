package com.example.navigationapp.presentation.ui.fragments.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.navigationapp.repository.location.Location
import com.example.navigationapp.repository.location.LocationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val location: LocationUseCase
): ViewModel() {
    val stateFlow = MutableStateFlow("Stop")

    fun startGetLocation() {
        stateFlow.value = "Start"
        viewModelScope.launch {
            val test = location.getPosition().collect {
                Log.d("MyLog", "$it")

            }
        }
    }

    fun stopLocation() {
        stateFlow.value = "Stop"
    }
}