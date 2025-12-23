package com.example.w.feature.charge

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.w.core.ui.components.PosActionButton

@Composable
fun ActionButtonArea(
    sidePadding: Dp,
    text: String,
    enabled: Boolean,
    loading: Boolean,
    onClick: () -> Unit
) {
    Spacer(modifier = Modifier.height(sidePadding))
    Spacer(modifier = Modifier.height(sidePadding))
    PosActionButton(
        text = text,
        onClick = onClick,
        enabled = enabled,
        containerColor = Color.White,
        contentColor = Color.Black,
        loading = loading
    )
}