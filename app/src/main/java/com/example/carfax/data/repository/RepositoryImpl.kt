package com.example.carfax.data.repository

import com.example.carfax.domain.util.Resource
import com.example.carfax.data.local_source.CarDao
import com.example.carfax.data.local_source.CarEntity
import com.example.carfax.data.remote_source.Api
import com.example.carfax.domain.mapper.CarListToDbMapper
import com.example.carfax.domain.repository.Repository
import com.example.carfax.domain.util.NetworkHelper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val api: Api,
    private val carDao: CarDao,
    private val networkHelper: NetworkHelper
) : Repository {

    override suspend fun getAllCars(): Flow<Resource<List<CarEntity>>> = flow {
        try {
            emit(Resource.Loading(true))

            if (networkHelper.isNetworkAvailable()) {

                val response = api.getAllCars()
                if (response.isSuccessful) {
                    response.body()?.let { carList ->
                        val carEntities = CarListToDbMapper.map(carList)
                        carDao.deleteAll()
                        carDao.insertAll(carEntities)
                        emit(Resource.Success(carEntities))
                    } ?: emit(Resource.Error("No data available"))
                } else {
                    emit(Resource.Error("Error: ${response.message()}"))
                }
            } else {
                val localCars = carDao.getAllCars().first()
                emit(Resource.Success(localCars))
            }
        } catch (e: Exception) {
            emit(Resource.Error("Exception: ${e.message}"))
        } finally {
            emit(Resource.Loading(false))
        }
    }
}