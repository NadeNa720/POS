package com.example.w.feature.charge

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import com.example.w.core.ui.components.PosActionButton
import com.example.w.core.ui.components.PosKeypadButton

@Composable
fun PinPanel(
    pin: String,
    onDigit: (Int) -> Unit,
    onDelete: () -> Unit,
    onClear: () -> Unit,
    onValidate: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxSize()) {
        // Верх: логотип, заголовок и точки
        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HeaderLogo()
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Enter PIN code",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = com.example.w.ui.theme.SFPro,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(12.dp))
            PinDots(pinLength = pin.length)
        }

        // Низ: клавиатура + Validate (зафиксированы у нижней границы)
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                color = Color(0xFF262626),
                contentColor = Color.White
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp, end = 8.dp, top = 16.dp, bottom = 12.dp),
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
                        PinKeypadDigit(1, onDigit, buttonModifier, enabled = pin.length < 4)
                        PinKeypadDigit(2, onDigit, buttonModifier, enabled = pin.length < 4)
                        PinKeypadDigit(3, onDigit, buttonModifier, enabled = pin.length < 4)
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        PinKeypadDigit(4, onDigit, buttonModifier, enabled = pin.length < 4)
                        PinKeypadDigit(5, onDigit, buttonModifier, enabled = pin.length < 4)
                        PinKeypadDigit(6, onDigit, buttonModifier, enabled = pin.length < 4)
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        PinKeypadDigit(7, onDigit, buttonModifier, enabled = pin.length < 4)
                        PinKeypadDigit(8, onDigit, buttonModifier, enabled = pin.length < 4)
                        PinKeypadDigit(9, onDigit, buttonModifier, enabled = pin.length < 4)
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        PosKeypadButton(text = "C", onClick = onClear, modifier = buttonModifier, enabled = true)
                        PinKeypadDigit(0, onDigit, buttonModifier, enabled = pin.length < 4)
                        PosKeypadButton(text = "⌫", onClick = onDelete, modifier = buttonModifier, enabled = true)
                    }
                }
            }

            // как на главном: два небольших отступа перед кнопкой
            Spacer(modifier = Modifier.height(12.dp))
            Spacer(modifier = Modifier.height(12.dp))

            PosActionButton(
                text = "Validate",
                onClick = onValidate,
                enabled = pin.length == 4,
                containerColor = Color.White,   // белая, когда enabled
                contentColor = Color.Black,
                loading = false
            )
        }
    }
}

@Composable
private fun PinDots(pinLength: Int) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(4) { index ->
            Box(
                modifier = Modifier
                    .size(18.dp)
                    .background(
                        color = if (index < pinLength) Color.White else Color(0xFF4A4A4A),
                        shape = RoundedCornerShape(50)
                    )
            )
        }
    }
}

@Composable
private fun PinKeypadDigit(
    digit: Int,
    onDigit: (Int) -> Unit,
    modifier: Modifier,
    enabled: Boolean = true
) {
    PosKeypadButton(
        text = digit.toString(),
        onClick = { if (enabled) onDigit(digit) },
        modifier = modifier,
        enabled = enabled
    )
}