package com.example.navigationapp.presentation.ui.fragments.result

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.navigationapp.repository.db.LocationDataBaseSource
import com.example.navigationapp.repository.db.LocationEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResultViewModel @Inject constructor(
    private val locationDataBaseSource: LocationDataBaseSource
) : ViewModel() {

    val locationState = MutableStateFlow(listOf<LocationEntity>())

    fun getAllItems() {
        viewModelScope.launch {
            locationDataBaseSource.getItems().collect {
                locationState.value = it
            }
        }
    }

    fun delItem(item: LocationEntity) {
        locationDataBaseSource.deleteSingleItems(item)
    }

    fun delAllItems() {
        locationDataBaseSource.deleteAllItems()
    }

}