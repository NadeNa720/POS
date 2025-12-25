package com.example.w.core.data.repository

import com.example.w.core.data.model.Transaction
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class TransactionRepository {
    private val db = Firebase.firestore

    suspend fun saveTransaction(transaction: Transaction) {
        try {
            android.util.Log.d("POS_FIREBASE", "Attempting to save transaction: $transaction")
            kotlinx.coroutines.withTimeout(5000L) {
                val data = hashMapOf(
                    "id" to transaction.id,
                    "amount" to transaction.amount,
                    "timestamp" to transaction.timestamp,
                    "status" to transaction.status,
                    "device" to "Android"
                )
                db.collection("transactions")
                    .document(transaction.id)
                    .set(data)
                    .await()
            }
            android.util.Log.d("POS_FIREBASE", "Transaction saved successfully!")
        } catch (e: Exception) {
            android.util.Log.e("POS_FIREBASE", "Error saving transaction", e)
            e.printStackTrace()
            // Rethrow so ViewModel knows it failed if needed, or suppress
        }
    }
}
