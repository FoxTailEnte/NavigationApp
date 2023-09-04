package com.example.navigationapp.repository.db

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

class LocationDataBaseImpl @Inject constructor(
    private val dao: Dao
) : LocationDataBaseSource {
    override fun saveLocation(location: LocationEntity) {
        val entity = LocationEntity(
            latitude = location.latitude,
            longitude = location.longitude,
            date = location.date
        )
        CoroutineScope(Dispatchers.IO).launch {
            dao.insertLocation(entity)
        }
    }

    override fun getItems(): Flow<List<LocationEntity>> =
        dao.getAllLocations()

    override fun deleteItems() {
        CoroutineScope(Dispatchers.IO).launch {
            dao.deleteOlderThan3Months()
        }
    }

    override fun deleteSingleItems(item: LocationEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            dao.deleteItemById(itemId = item.id.toInt())
        }
    }

    override fun deleteAllItems() {
        CoroutineScope(Dispatchers.IO).launch {
            dao.deleteAllItems()
        }
    }
}