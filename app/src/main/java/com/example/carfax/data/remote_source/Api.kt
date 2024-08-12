package com.example.carfax.data.remote_source

import com.example.carfax.domain.model.CarList
import retrofit2.Response
import retrofit2.http.GET

interface Api {

    @GET("assignment.json")
    suspend fun getAllCars(): Response<CarList>

}