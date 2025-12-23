package com.example.w.feature.charge

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.ui.unit.dp

@Composable
fun BottomPanel(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(190.dp)
            .border(
                width = 2.dp,
                color = Color(0xFF5C5C5C),
                shape = RoundedCornerShape(24.dp)
            )
    ) {
        ChipIllustration(
            modifier = Modifier
                .align(Alignment.Center)
                .width(96.dp)
                .height(120.dp)
        )

        // Central line continuation (goes down)
        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .offset(y = 60.dp) // start from chip bottom (chip height = 120dp)
                .width(2.dp)
                .height(110.dp)
                .background(Color(0xFF5C5C5C))
        )
    }
}