package com.example.carfax.data.local_source

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cars")
data class CarEntity(
    @PrimaryKey val id: String,
    val year: Int,
    val make: String,
    val model: String,
    val trim: String,
    val currentPrice: Int,
    val mileage: Int,
    val exteriorColor: String,
    val interiorColor: String,
    val drivetype: String,
    val transmission: String,
    val bodytype: String,
    val engine: String,
    val fuel: String,
    val dealerCity: String,
    val dealerState: String,
    val imageUrl: String,
    val phone: String,
    val address: String,
    val displacement: String,
)