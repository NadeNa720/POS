package com.example.w.feature.charge

import androidx.compose.foundation.border
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DeclinedInsufficientFundsOverlay() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFF3B30))
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Declined",
                color = Color.White,
                fontSize = 32.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
                fontFamily = com.example.w.ui.theme.SFPro
            )

            Spacer(modifier = Modifier.height(24.dp))

            Box(
                modifier = Modifier
                    .size(140.dp)
                    .border(width = 3.dp, color = Color.White, shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "✕",
                    color = Color.White,
                    fontSize = 64.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = com.example.w.ui.theme.SFPro
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Единственная строка причины
            Text(
                text = "Insufficient funds",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center,
                fontFamily = com.example.w.ui.theme.SFPro
            )
        }
    }
}

@Composable
fun DeclinedServerErrorOverlay() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFF3B30))
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Declined",
                color = Color.White,
                fontSize = 32.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
                fontFamily = com.example.w.ui.theme.SFPro
            )

            Spacer(modifier = Modifier.height(24.dp))

            Box(
                modifier = Modifier
                    .size(140.dp)
                    .border(width = 3.dp, color = Color.White, shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "✕",
                    color = Color.White,
                    fontSize = 64.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = com.example.w.ui.theme.SFPro
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Единственная строка причины
            Text(
                text = "Problem with server",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center,
                fontFamily = com.example.w.ui.theme.SFPro
            )
        }
    }
}