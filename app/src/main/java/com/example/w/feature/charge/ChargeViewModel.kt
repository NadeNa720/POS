package com.example.w.feature.charge

import androidx.lifecycle.ViewModel
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
                    // Limit input length to avoid overflow or unrealistic amounts
                    if (current.amountCents < 1_000_000_00) { // Max 1 million
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
                // TODO: Navigate to Payment or next step
            }
        }
    }

    /*
     * Formats the amount in cents to a string like "€0.00"
     */
    fun formatAmount(amountCents: Long): String {
        val whole = amountCents / 100
        val fraction = amountCents % 100
        val symbol = _state.value.currencySymbol
        // Ensure fraction is always 2 digits
        val fractionString = if (fraction < 10) "0$fraction" else "$fraction"
        return "$symbol$whole.$fractionString"
    }
}
