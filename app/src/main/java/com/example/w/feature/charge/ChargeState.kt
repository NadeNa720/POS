package com.example.w.feature.charge

data class ChargeState(
    /**
     * Amount in cents.
     * Example: 1234 -> €12.34
     */
    val amountCents: Long = 0,
    val currencySymbol: String = "€"
)
