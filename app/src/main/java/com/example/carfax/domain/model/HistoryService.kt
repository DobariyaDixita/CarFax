package com.example.carfax.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class HistoryService(
    val city: String,
    val date: String,
    val description: String,
    val odometerReading: Int,
    val source: String,
    val state: String
):Parcelable