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
        // Верх: логотип + заголовок
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
            // Убрали PinDots из верхнего блока — теперь они снизу
        }

        // Низ: клавиатура + Validate
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .navigationBarsPadding() // максимально вниз, но безопасно
                .padding(bottom = 4.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Крупные "круглешки" прямо над клавиатурой
            PinDots(pinLength = pin.length)
            Spacer(modifier = Modifier.height(16.dp))

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
                        PinKeypadDigit(1, onDigit, buttonModifier)
                        PinKeypadDigit(2, onDigit, buttonModifier)
                        PinKeypadDigit(3, onDigit, buttonModifier)
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        PinKeypadDigit(4, onDigit, buttonModifier)
                        PinKeypadDigit(5, onDigit, buttonModifier)
                        PinKeypadDigit(6, onDigit, buttonModifier)
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        PinKeypadDigit(7, onDigit, buttonModifier)
                        PinKeypadDigit(8, onDigit, buttonModifier)
                        PinKeypadDigit(9, onDigit, buttonModifier)
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        PosKeypadButton(text = "C", onClick = onClear, modifier = buttonModifier)
                        PinKeypadDigit(0, onDigit, buttonModifier)
                        PosKeypadButton(text = "⌫", onClick = onDelete, modifier = buttonModifier)
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            PosActionButton(
                text = "Validate",
                onClick = onValidate,
                enabled = pin.length == 4,
                containerColor = Color(0xFFBDBDBD),
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
                    .size(18.dp) // увеличил размер "круглешков"
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
    modifier: Modifier
) {
    PosKeypadButton(
        text = digit.toString(),
        onClick = { onDigit(digit) },
        modifier = modifier
    )
}