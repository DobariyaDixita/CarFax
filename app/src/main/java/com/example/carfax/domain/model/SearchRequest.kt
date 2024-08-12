package com.example.carfax.domain.model

data class SearchRequest(
    val make: String,
    val mileageRange: MileageRangeSearchRequest,
    val model: String,
    val priceRange: PriceRange,
    val radius: Int,
    val srpUrl: String,
    val webHost: String,
    val yearRange: YearRangeSearchRequest,
    val zip: String
)