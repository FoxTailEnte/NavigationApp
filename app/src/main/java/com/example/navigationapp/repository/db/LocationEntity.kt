package com.example.navigationapp.repository.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.navigationapp.repository.db.LocationEntity.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class LocationEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo(name = "latitude")
    val latitude: String,
    @ColumnInfo(name = "longitude")
    val longitude: String,
    @ColumnInfo(name = "date")
    val date: String,
) {
    companion object {
        const val TABLE_NAME = "location_entity_table"
    }
}
