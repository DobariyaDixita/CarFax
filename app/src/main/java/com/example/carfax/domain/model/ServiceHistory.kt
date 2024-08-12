package com.example.carfax.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ServiceHistory(
    val history: List<HistoryService>,
    val icon: String,
    val iconUrl: String,
    val number: Int,
    val text: String
): Parcelable