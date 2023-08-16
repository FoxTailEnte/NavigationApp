package com.example.navigationapp.repository.di

import android.content.Context
import android.location.LocationManager
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
    @Singleton
    fun provideLocation(context: Context): LocationUseCase =
        Location(context)

    @Provides
    @Singleton
    fun provideLocationManager(context: Context) =
        context.getSystemService(Context.LOCATION_SERVICE) as LocationManager

    @Provides
    fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    @Singleton
    fun provideContext(@ApplicationContext context: Context): Context = context
}