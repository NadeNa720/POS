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
    val isApproved: Boolean = false,
    val showSettingsPanel: Boolean = false,
    val languageCode: String = "EN",
    val isDeclined: Boolean = false,
    // Новые флаги для двух разных окон "Declined"
    val isDeclinedInsufficientFunds: Boolean = false,
    val isDeclinedServerError: Boolean = false
)
