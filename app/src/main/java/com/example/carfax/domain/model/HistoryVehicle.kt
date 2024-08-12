package com.example.carfax.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class HistoryVehicle(
    val averageMilesPerYear: Int,
    val ownerNumber: Int,
    val useType: String
):Parcelable