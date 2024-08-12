package com.example.carfax.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AccidentHistory(
    val accidentSummary: List<String>,
    val icon: String,
    val iconUrl: String,
    val text: String
): Parcelable