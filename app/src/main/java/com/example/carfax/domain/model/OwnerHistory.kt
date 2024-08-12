package com.example.carfax.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class OwnerHistory(
    val history: List<History>,
    val icon: String,
    val iconUrl: String,
    val text: String
): Parcelable