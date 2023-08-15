package com.example.navigationapp.repository.location

import kotlinx.coroutines.flow.Flow


interface LocationUseCase {
    fun getPosition(): Flow<String>
}