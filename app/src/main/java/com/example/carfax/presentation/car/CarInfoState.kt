package com.example.carfax.presentation.car

import com.example.carfax.data.local_source.CarEntity

data class CarInfoState(
    val carList: List<CarEntity> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)