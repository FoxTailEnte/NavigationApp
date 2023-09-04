package com.example.navigationapp.repository.di

import android.content.Context
import android.location.LocationManager
import androidx.room.Room
import com.example.navigationapp.repository.db.LocationDataBase
import com.example.navigationapp.repository.db.LocationDataBaseImpl
import com.example.navigationapp.repository.db.LocationDataBaseSource
import com.example.navigationapp.repository.location.Location
import com.example.navigationapp.repository.location.LocationUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocationModule {

    @Provides
    fun provideLocation(context: Context): LocationUseCase =
        Location(context)

    @Provides
    fun provideLocationManager(context: Context) =
        context.getSystemService(Context.LOCATION_SERVICE) as LocationManager

    @Provides
    fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    @Singleton
    fun provideContext(@ApplicationContext context: Context): Context = context

    @Provides
    fun provideLocationDataBaseUseCase(dataBase: LocationDataBase): LocationDataBaseSource =
        LocationDataBaseImpl(dataBase.getDao())

    @Provides
    @Singleton
    fun provideLocationDataBase(context: Context): LocationDataBase =
        Room.databaseBuilder(
            context,
            LocationDataBase::class.java,
            "location_database"
        ).build()
}