package com.example.navigationapp.repository.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {

    @Insert
    suspend fun insertLocation(location: LocationEntity)

    @Query("SELECT * FROM ${LocationEntity.TABLE_NAME} WHERE date >= DATE('now', '-3 months')")
    fun getAllLocations(): Flow<List<LocationEntity>>

    @Query("DELETE FROM ${LocationEntity.TABLE_NAME} WHERE date < DATE('now', '-3 months')")
    fun deleteOlderThan3Months()

    @Query("DELETE FROM ${LocationEntity.TABLE_NAME} WHERE id = :itemId")
    fun deleteItemById(itemId: Int)

    @Query("DELETE FROM ${LocationEntity.TABLE_NAME}")
    fun deleteAllItems()
}