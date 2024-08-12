package com.example.carfax.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class OnePriceArrow(
    val arrow: String,
    val arrowUrl: String,
    val icon: String,
    val iconUrl: String,
    val order: Int,
    val text: String
): Parcelable