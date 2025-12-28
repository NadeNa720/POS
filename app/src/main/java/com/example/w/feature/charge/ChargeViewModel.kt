package com.example.w.feature.charge

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ChargeViewModel : ViewModel() {

    private val _state = MutableStateFlow(ChargeState())
    val state: StateFlow<ChargeState> = _state.asStateFlow()

    fun onEvent(event: ChargeEvent) {
        when (event) {
            is ChargeEvent.EnterDigit -> {
                _state.update { current ->
                    if (current.amountCents < 1_000_000_00) {
                        current.copy(amountCents = current.amountCents * 10 + event.digit)
                    } else {
                        current
                    }
                }
            }

            ChargeEvent.DeleteLast -> {
                _state.update { current ->
                    current.copy(amountCents = current.amountCents / 10)
                }
            }

            ChargeEvent.Clear -> {
                _state.update { current ->
                    current.copy(amountCents = 0)
                }
            }

            ChargeEvent.Charge -> {
                viewModelScope.launch {
                    // Старт процесса: показываем загрузку, скрываем панели
                    _state.update { it.copy(isCharging = true, showPaymentPanel = false, showPinPanel = false) }
                    // Эмуляция ожидания SDK/сети
                    delay(1500)
                    // Отображаем панель оплаты
                    _state.update { it.copy(isCharging = false, showPaymentPanel = true) }
                    // Через 3 секунды — экран ввода PIN
                    delay(3000)
                    _state.update { it.copy(showPaymentPanel = false, showPinPanel = true) }
                }
            }

            ChargeEvent.Back -> {
                _state.update {
                    it.copy(
                        showPaymentPanel = false,
                        showPinPanel = false,
                        isCharging = false,
                        amountCents = 0,
                        pin = ""
                    )
                }
            }

            is ChargeEvent.PinEnterDigit -> {
                _state.update { s ->
                    if (s.pin.length < 4) s.copy(pin = s.pin + event.digit.toString()) else s
                }
            }

            ChargeEvent.PinDeleteLast -> {
                _state.update { s ->
                    s.copy(pin = if (s.pin.isNotEmpty()) s.pin.dropLast(1) else s.pin)
                }
            }

            ChargeEvent.PinClear -> {
                _state.update { it.copy(pin = "") }
            }

            ChargeEvent.PinValidate -> {
                viewModelScope.launch {
                    _state.update { it.copy(showPinPanel = false, isAuthorizing = true, isApproved = false, isDeclined = false) }
                    delay(2000)
                    _state.update { it.copy(isAuthorizing = false, isApproved = true, isDeclined = false) }
                    delay(2500)
                    _state.update {
                        it.copy(
                            isApproved = false,
                            isDeclined = false,
                            pin = "",
                            amountCents = 0,
                            showPaymentPanel = false,
                            showPinPanel = false
                        )
                    }
                }
            }
            ChargeEvent.PinDecline -> {
                viewModelScope.launch {
                    _state.update { it.copy(showPinPanel = false, isAuthorizing = false, isApproved = false, isDeclined = true) }
                    delay(2500)
                    _state.update {
                        it.copy(
                            isApproved = false,
                            isDeclined = false,
                            pin = "",
                            amountCents = 0,
                            showPaymentPanel = false,
                            showPinPanel = false
                        )
                    }
                }
            }
            ChargeEvent.SettingsOpen -> {
                _state.update { it.copy(showSettingsPanel = true) }
            }
            ChargeEvent.SettingsClose -> {
                _state.update { it.copy(showSettingsPanel = false) }
            }
            is ChargeEvent.SettingsChangeLanguage -> {
                _state.update { it.copy(languageCode = event.code) }
            }
        }
    }

    fun formatAmount(amountCents: Long): String {
        val whole = amountCents / 100
        val fraction = amountCents % 100
        val symbol = _state.value.currencySymbol
        val fractionString = if (fraction < 10) "0$fraction" else "$fraction"
        return "$symbol$whole.$fractionString"
    }

    fun applyDebugRoute(screen: String?, amountCents: Long?) {
        _state.update { s ->
            var n = s
            if (amountCents != null) {
                n = n.copy(amountCents = amountCents)
            }
            when (screen) {
                null, "main" -> n.copy(
                    isCharging = false,
                    showPaymentPanel = false,
                    showPinPanel = false,
                    isAuthorizing = false,
                    isApproved = false,
                    isDeclined = false,
                    isDeclinedInsufficientFunds = false,
                    isDeclinedServerError = false,
                    pin = ""
                )
                "payment" -> n.copy(
                    showPaymentPanel = true,
                    showPinPanel = false,
                    isAuthorizing = false,
                    isApproved = false,
                    isDeclined = false,
                    isDeclinedInsufficientFunds = false,
                    isDeclinedServerError = false
                )
                "pin" -> n.copy(
                    showPaymentPanel = false,
                    showPinPanel = true,
                    isAuthorizing = false,
                    isApproved = false,
                    isDeclined = false,
                    isDeclinedInsufficientFunds = false,
                    isDeclinedServerError = false
                )
                "auth" -> n.copy(
                    showPaymentPanel = false,
                    showPinPanel = false,
                    isAuthorizing = true,
                    isApproved = false,
                    isDeclined = false,
                    isDeclinedInsufficientFunds = false,
                    isDeclinedServerError = false
                )
                "approved" -> n.copy(
                    showPaymentPanel = false,
                    showPinPanel = false,
                    isAuthorizing = false,
                    isApproved = true,
                    isDeclined = false,
                    isDeclinedInsufficientFunds = false,
                    isDeclinedServerError = false
                )
                "declined" -> n.copy( // общий вариант, если останется
                    showPaymentPanel = false,
                    showPinPanel = false,
                    isAuthorizing = false,
                    isApproved = false,
                    isDeclined = true,
                    isDeclinedInsufficientFunds = false,
                    isDeclinedServerError = false
                )
                "declined_insufficient" -> n.copy(
                    showPaymentPanel = false,
                    showPinPanel = false,
                    isAuthorizing = false,
                    isApproved = false,
                    isDeclined = false,
                    isDeclinedInsufficientFunds = true,
                    isDeclinedServerError = false
                )
                "declined_server" -> n.copy(
                    showPaymentPanel = false,
                    showPinPanel = false,
                    isAuthorizing = false,
                    isApproved = false,
                    isDeclined = false,
                    isDeclinedInsufficientFunds = false,
                    isDeclinedServerError = true
                )
                else -> n
            }
        }
    }
}
