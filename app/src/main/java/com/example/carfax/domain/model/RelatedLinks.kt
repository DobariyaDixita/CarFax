package com.example.carfax.domain.model

import com.google.gson.annotations.SerializedName


data class RelatedLinks(

    @SerializedName("Fiat    124   Spider Trim   Levels") var Fiat_Levels: ArrayList<Fiat124SpiderTrimLevel> = arrayListOf(),
    @SerializedName("Similar Cars") var SimilarCars: ArrayList<SimilarCar> = arrayListOf(),
    @SerializedName("Used    Fiat  124    Spider by      Year") var UsedFiat124SpiderbyYear: ArrayList<UsedFiat124SpiderByYear> = arrayListOf()
)

