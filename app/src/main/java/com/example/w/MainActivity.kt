package com.example.w

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.w.feature.charge.ChargeScreen
import com.example.w.ui.theme.WTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val initialScreen = intent.getStringExtra("screen")
                    val initialAmount = intent.getLongExtra("amountCents", -1L).takeIf { it >= 0 }
                    ChargeScreen(
                        initialScreen = initialScreen,
                        initialAmountCents = initialAmount,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}