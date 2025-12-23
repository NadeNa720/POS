package com.example.w.feature.charge

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Text

@Composable
fun PaymentPanel(
    amountText: String,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {}
) {
    val arrowOffset = remember { Animatable(0f) }
    LaunchedEffect(Unit) {
        while (true) {
            arrowOffset.animateTo(
                8f,
                animationSpec = tween(durationMillis = 800, easing = LinearEasing)
            )
            arrowOffset.animateTo(
                0f,
                animationSpec = tween(durationMillis = 800, easing = LinearEasing)
            )
        }
    }

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        BackHandle(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .offset(x = (-28).dp),
            onBackClick = onBackClick
        )
        // Arrow + hint (top)
        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(y = 6.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "↑",
                color = Color.White,
                fontSize = 24.sp,
                fontFamily = com.example.w.ui.theme.SFPro,
                modifier = Modifier.offset(y = (-arrowOffset.value).dp)
            )
            Text(
                text = "Tap at the top",
                color = Color(0xFFBDBDBD),
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = com.example.w.ui.theme.SFPro
            )
        }

        // Amount + hint (center)
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = amountText,
                fontSize = 54.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
                letterSpacing = (-0.25).sp,
                fontFamily = com.example.w.ui.theme.SFPro,
                color = Color.White,
                style = androidx.compose.ui.text.TextStyle(fontFeatureSettings = "tnum,lnum")
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Tap, insert, or swipe",
                color = Color(0xFFBDBDBD),
                fontSize = 20.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = com.example.w.ui.theme.SFPro
            )
        }

        // Bottom panel with chip
        BottomPanel(modifier = Modifier.align(Alignment.BottomCenter))
    }
}