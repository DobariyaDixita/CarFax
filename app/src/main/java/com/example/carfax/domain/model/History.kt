package com.example.carfax.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class History(
    val city: String,
    val endOwnershipDate: String,
    val ownerNumber: Int,
    val purchaseDate: String,
    val state: String
):Parcelable