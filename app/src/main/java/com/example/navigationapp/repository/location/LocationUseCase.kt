package com.example.navigationapp.repository.location

import kotlinx.coroutines.flow.Flow


interface LocationUseCase {

    fun checkPermission(): Flow<Boolean>
    fun getPosition(): Flow<String>
    fun stopGettingLocation()
}