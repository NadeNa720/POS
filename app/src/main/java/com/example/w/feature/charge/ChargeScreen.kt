package com.example.w.feature.charge

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.w.core.ui.components.PosActionButton
import com.example.w.core.ui.components.PosKeypadButton

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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Header
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Close Icon (using "x" text for now as simple substitute or icon if available)
                // Using Text for simplicity as per request for "runnable code" without extra icon resources
                Text(
                    text = "x", 
                    color = Color.White, 
                    fontSize = 24.sp, 
                    fontWeight = FontWeight.Light,
                    modifier = Modifier.padding(8.dp)
                )
                
                Text(
                    text = "Settings",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal
                )
            }

            // Display Area
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Total amount",
                    fontSize = 16.sp,
                    color = Color(0xFF888888),
                    fontWeight = FontWeight.Medium
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = viewModel.formatAmount(state.amountCents),
                    fontSize = 48.sp,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center,
                    letterSpacing = (-0.25).sp,
                    fontFamily = com.example.w.ui.theme.SFPro,
                    color = if (state.amountCents == 0L) Color(0xFF9A9A9A) else Color.White,
                    style = androidx.compose.ui.text.TextStyle(fontFeatureSettings = "tnum,lnum")
                )
            }

            // Keypad Area — контейнер «поле» ближе к краям и с меньшим скруглением
            androidx.compose.material3.Surface(
                modifier = Modifier
                    .fillMaxWidth(),
                shape = androidx.compose.foundation.shape.RoundedCornerShape(8.dp),
                color = Color(0xFF262626),
                contentColor = Color.White
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val buttonModifier = Modifier
                        .weight(1f)
                        .height(60.dp)

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        KeypadDigit(1, viewModel, buttonModifier)
                        KeypadDigit(2, viewModel, buttonModifier)
                        KeypadDigit(3, viewModel, buttonModifier)
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        KeypadDigit(4, viewModel, buttonModifier)
                        KeypadDigit(5, viewModel, buttonModifier)
                        KeypadDigit(6, viewModel, buttonModifier)
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        KeypadDigit(7, viewModel, buttonModifier)
                        KeypadDigit(8, viewModel, buttonModifier)
                        KeypadDigit(9, viewModel, buttonModifier)
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        PosKeypadButton(
                            text = "C",
                            onClick = { viewModel.onEvent(ChargeEvent.Clear) },
                            modifier = buttonModifier
                        )
                        KeypadDigit(0, viewModel, buttonModifier)
                        PosKeypadButton(
                            text = "⌫",
                            onClick = { viewModel.onEvent(ChargeEvent.DeleteLast) },
                            modifier = buttonModifier
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(18.dp))

            PosActionButton(
                text = "Charge ${viewModel.formatAmount(state.amountCents)}",
                onClick = { viewModel.onEvent(ChargeEvent.Charge) },
                enabled = state.amountCents > 0L,
                containerColor = Color.White,
                contentColor = Color.Black
            )
        }
    }

}

@Composable
private fun KeypadDigit(
    digit: Int,
    viewModel: ChargeViewModel,
    modifier: Modifier
) {
    PosKeypadButton(
        text = digit.toString(),
        onClick = { viewModel.onEvent(ChargeEvent.EnterDigit(digit)) },
        modifier = modifier
    )
}
