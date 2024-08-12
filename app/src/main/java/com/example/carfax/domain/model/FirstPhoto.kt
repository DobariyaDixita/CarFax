package com.example.carfax.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FirstPhoto(
    val large: String,
    val medium: String,
    val small: String
):Parcelable