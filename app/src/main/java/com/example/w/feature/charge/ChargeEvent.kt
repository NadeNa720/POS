package com.example.w.feature.charge

sealed interface ChargeEvent {
    data class EnterDigit(val digit: Int) : ChargeEvent
    data object DeleteLast : ChargeEvent
    data object Clear : ChargeEvent
    data object Charge : ChargeEvent
}
