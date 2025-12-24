package com.example.w.feature.charge

data class ChargeState(
    val amountCents: Long = 0,
    val currencySymbol: String = "€",
    val isCharging: Boolean = false,
    val showPaymentPanel: Boolean = false,
    // Добавлено для PIN-экрана
    val showPinPanel: Boolean = false,
    val pin: String = "",
    val isAuthorizing: Boolean = false,
    val isApproved: Boolean = false
)
