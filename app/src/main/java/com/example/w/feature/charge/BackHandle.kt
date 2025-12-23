package com.example.w.feature.charge

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Alignment
import androidx.compose.material3.Text
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BackHandle(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit
) {
    val shape = RoundedCornerShape(topEnd = 24.dp, bottomEnd = 24.dp)

    Box(
        modifier = modifier
            .width(56.dp)
            .height(120.dp)
            .background(color = Color(0xFF262626), shape = shape)
            .border(width = 2.dp, color = Color(0xFF5C5C5C), shape = shape)
            .clickable { onBackClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "←",
            fontSize = 24.sp,
            color = Color.White,
            fontFamily = com.example.w.ui.theme.SFPro
        )
    }
}