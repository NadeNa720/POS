package com.example.w.feature.charge

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ChargeScreen(
    viewModel: ChargeViewModel = viewModel(),
    initialScreen: String? = null,
    initialAmountCents: Long? = null,
    modifier: Modifier = Modifier
) {
    val state: ChargeState by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        if (initialScreen != null || initialAmountCents != null) {
            viewModel.applyDebugRoute(initialScreen, initialAmountCents)
        }
    }

    Surface(
        modifier = modifier.fillMaxSize(),
        color = Color(0xFF161616),
        contentColor = Color.White
    ) {
        val sidePadding = 12.dp

        when {
            // Если открыт Settings — показываем его поверх
            state.showSettingsPanel -> {
                SettingsPanel(
                    language = state.languageCode,
                    onLanguageSelect = { code -> viewModel.onEvent(ChargeEvent.SettingsChangeLanguage(code)) },
                    onCurrencySelect = { symbol -> viewModel.onEvent(ChargeEvent.SettingsChangeCurrency(symbol)) },
                    onClose = { viewModel.onEvent(ChargeEvent.SettingsClose) },
                    onLogout = { viewModel.onEvent(ChargeEvent.Logout) },
                    modifier = Modifier.fillMaxSize()
                )
            }
            state.isAuthorizing -> {
                AuthorizingOverlay()
            }
            state.isApproved -> {
                ApprovedOverlay(syncStatus = state.debugSyncStatus)
            }
            // Платёжная панель после нажатия Charge
            state.showPaymentPanel -> {
                PaymentPanel(
                    amountText = viewModel.formatAmount(state.amountCents),
                    onBackClick = { viewModel.onEvent(ChargeEvent.Back) },
                    modifier = Modifier.fillMaxSize()
                )
            }
            // Экран ввода PIN
            state.showPinPanel -> {
                PinPanel(
                    pin = state.pin,
                    onDigit = { d -> viewModel.onEvent(ChargeEvent.PinEnterDigit(d)) },
                    onDelete = { viewModel.onEvent(ChargeEvent.PinDeleteLast) },
                    onClear = { viewModel.onEvent(ChargeEvent.PinClear) },
                    onValidate = { viewModel.onEvent(ChargeEvent.PinValidate) },
                    modifier = Modifier.fillMaxSize()
                )
            }
            // Два отдельных окна отказа
            state.isDeclinedInsufficientFunds -> {
                DeclinedInsufficientFundsOverlay()
            }
            state.isDeclinedServerError -> {
                DeclinedServerErrorOverlay()
            }
            else -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = sidePadding, vertical = 24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    HeaderLogo(
                        trailing = {
                            Text(
                                text = "Settings",
                                color = Color.White,
                                modifier = Modifier
                                    .padding(start = 8.dp)
                                    .clickable { viewModel.onEvent(ChargeEvent.SettingsOpen) }
                            )
                        }
                    )
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
                    ) { viewModel.onEvent(ChargeEvent.Charge) }
                }
            }
        }
    }
}