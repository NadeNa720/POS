package com.example.w.feature.charge

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ChargeScreen(
    viewModel: ChargeViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    val state by viewModel.state.collectAsState()

    Surface(
        modifier = modifier.fillMaxSize(),
        color = Color(0xFF161616),
        contentColor = Color.White
    ) {
        val sidePadding = 12.dp

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = sidePadding, vertical = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when {
                state.showPinPanel -> {
                    PinPanel(
                        pin = state.pin,
                        onDigit = { d -> viewModel.onEvent(ChargeEvent.PinEnterDigit(d)) },
                        onDelete = { viewModel.onEvent(ChargeEvent.PinDeleteLast) },
                        onClear = { viewModel.onEvent(ChargeEvent.PinClear) },
                        onValidate = { viewModel.onEvent(ChargeEvent.PinValidate) },
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                    )
                }
                state.showPaymentPanel -> {
                    PaymentPanel(
                        amountText = viewModel.formatAmount(state.amountCents),
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth(),
                        onBackClick = { viewModel.onEvent(ChargeEvent.Back) }
                    )
                }
                else -> {
                    HeaderLogo()
                    AmountText(
                        text = viewModel.formatAmount(state.amountCents),
                        isZero = state.amountCents == 0L,
                        modifier = Modifier.weight(1f)
                    )
                    Keypad(viewModel)
                    ActionButtonArea(
                        sidePadding = sidePadding,
                        text = "Charge ${viewModel.formatAmount(state.amountCents)}",
                        enabled = state.amountCents > 0L,
                        loading = state.isCharging
                    ) {
                        viewModel.onEvent(ChargeEvent.Charge)
                    }
                }
            }
        }
    }
}