package com.example.carfax.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class VehicleUseHistory(
    val history: List<HistoryVehicle>,
    val icon: String,
    val iconUrl: String,
    val text: String
): Parcelable
