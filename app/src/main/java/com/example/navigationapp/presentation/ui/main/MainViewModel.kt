package com.example.navigationapp.presentation.ui.main

import androidx.lifecycle.ViewModel
import com.example.navigationapp.repository.db.LocationDataBaseSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val locationDataBaseSource: LocationDataBaseSource
): ViewModel() {

    fun deleteItems() {
        locationDataBaseSource.deleteItems()
    }
}