package com.example.w.feature.charge

sealed class ChargeEvent {
    data class EnterDigit(val digit: Int) : ChargeEvent()
    object DeleteLast : ChargeEvent()
    object Clear : ChargeEvent()
    object Charge : ChargeEvent()
    object Back : ChargeEvent()
    // PIN события
    data class PinEnterDigit(val digit: Int) : ChargeEvent()
    object PinDeleteLast : ChargeEvent()
    object PinClear : ChargeEvent()
    object PinValidate : ChargeEvent()
    object PinDecline : ChargeEvent()
    object SettingsOpen : ChargeEvent()
    object SettingsClose : ChargeEvent()
    data class SettingsChangeLanguage(val code: String) : ChargeEvent()
}
