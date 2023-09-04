package com.example.navigationapp.repository.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [LocationEntity::class], version = 1, exportSchema = true)
abstract class LocationDataBase: RoomDatabase() {
    abstract fun getDao(): Dao
}