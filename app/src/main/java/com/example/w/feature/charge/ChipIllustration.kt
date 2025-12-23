package com.example.w.feature.charge

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.Canvas
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp

@Composable
fun ChipIllustration(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier) {
        val strokeOuter = 3.dp.toPx()
        val strokeInner = 2.dp.toPx()
        val color = Color(0xFF5C5C5C)
        val corner = 12.dp.toPx()

        val w = size.width
        val h = size.height

        // Outer
        drawRoundRect(
            color = color,
            size = Size(w, h),
            style = Stroke(width = strokeOuter),
            cornerRadius = CornerRadius(corner, corner)
        )

        val left = strokeOuter / 2f
        val right = w - strokeOuter / 2f
        val top = strokeOuter / 2f
        val bottom = h - strokeOuter / 2f

        val xL = w * 0.28f
        val xC = w * 0.50f
        val xR = w * 0.72f

        val yTop = h * 0.36f
        val yBottom = h * 0.72f

        // Vertical lines
        drawLine(color = color, start = Offset(xL, top), end = Offset(xL, bottom), strokeWidth = strokeInner)
        drawLine(color = color, start = Offset(xC, top), end = Offset(xC, bottom), strokeWidth = strokeInner)
        drawLine(color = color, start = Offset(xR, top), end = Offset(xR, bottom), strokeWidth = strokeInner)

        // Top horizontal with center gap
        drawLine(color = color, start = Offset(left, yTop), end = Offset(xC, yTop), strokeWidth = strokeInner)
        drawLine(color = color, start = Offset(xC, yTop), end = Offset(right, yTop), strokeWidth = strokeInner)

        // No bottom horizontal; extend center vertical to chip bottom
        drawLine(color = color, start = Offset(xC, yBottom), end = Offset(xC, bottom), strokeWidth = strokeInner)
    }
}