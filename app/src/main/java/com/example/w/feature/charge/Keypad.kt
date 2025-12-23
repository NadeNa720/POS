package com.example.w.feature.charge

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material3.Surface
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import com.example.w.core.ui.components.PosKeypadButton

@Composable
fun Keypad(viewModel: ChargeViewModel) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        color = Color(0xFF262626),
        contentColor = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp, top = 16.dp, bottom = 8.dp),
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