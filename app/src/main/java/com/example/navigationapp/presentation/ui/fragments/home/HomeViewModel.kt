package com.example.navigationapp.presentation.ui.fragments.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.navigationapp.repository.location.LocationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val location: LocationUseCase
) : ViewModel() {
    val locationState = MutableStateFlow("0")
    val permissionState = MutableSharedFlow<Boolean>()
    val state = MutableStateFlow(BtState.STOP)

    fun checkPermission() {
        viewModelScope.launch {
            location.checkPermission().collect {
                permissionState.emit(it)
            }
        }
    }

    fun stopLocation() {
        state.value = BtState.STOP
        locationState.value = "0"
        location.stopGettingLocation()
    }

    enum class BtState {
        START,
        STOP
    }
}