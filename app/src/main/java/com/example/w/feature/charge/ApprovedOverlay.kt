package com.example.w.feature.charge

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.app

@Composable
fun ApprovedOverlay(syncStatus: String = "") {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Approved",
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
                    text = "✓",
                    color = Color.White,
                    fontSize = 64.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = com.example.w.ui.theme.SFPro
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Thank you!",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center,
                fontFamily = com.example.w.ui.theme.SFPro
            )

            if (syncStatus.isNotEmpty()) {
                Spacer(modifier = Modifier.height(32.dp))
                Text(
                    text = syncStatus,
                    color = Color.Yellow,
                    fontSize = 16.sp,
                    fontFamily = com.example.w.ui.theme.SFPro
                )
            }
        }
    }
}