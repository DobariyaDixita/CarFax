package com.example.carfax.domain.repository

import com.example.carfax.domain.util.Resource
import com.example.carfax.data.local_source.CarEntity
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun getAllCars(): Flow<Resource<List<CarEntity>>>
}