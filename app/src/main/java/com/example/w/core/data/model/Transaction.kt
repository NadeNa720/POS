package com.example.w.core.data.model

data class Transaction(
    val id: String = "",
    val amount: Long = 0,
    val timestamp: Long = 0,
    val status: String = "PENDING"
)
