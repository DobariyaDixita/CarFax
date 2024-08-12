package com.example.carfax.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MonthlyPaymentEstimate(
    val downPaymentAmount: Double,
    val downPaymentPercent: Int,
    val interestRate: Int,
    val loanAmount: Double,
    val monthlyPayment: Double,
    val price: Int,
    val termInMonths: Int
): Parcelable