package com.example.carfax.domain.usecase

import com.example.carfax.domain.util.Resource
import com.example.carfax.data.local_source.CarEntity
import com.example.carfax.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllCarsUsecase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(): Flow<Resource<List<CarEntity>>> = repository.getAllCars()
}