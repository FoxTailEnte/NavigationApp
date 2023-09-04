package com.example.navigationapp.repository.db

import kotlinx.coroutines.flow.Flow


interface LocationDataBaseSource {
    fun saveLocation(location: LocationEntity)
    fun getItems(): Flow<List<LocationEntity>>
    fun deleteItems()
    fun deleteSingleItems(item: LocationEntity)
    fun deleteAllItems()
}