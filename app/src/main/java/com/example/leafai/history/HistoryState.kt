
package com.example.leafai.history

import android.content.Context
import androidx.compose.runtime.mutableStateListOf

object HistoryState {

    val historyList = mutableStateListOf<DetectionHistory>()
    var selectedHistory: DetectionHistory? = null

    fun loadHistory(context: Context) {
        val savedHistory = HistoryStorage.loadHistory(context)
        val validHistory = HistoryStorage.removeExpiredHistory(savedHistory)

        historyList.clear()
        historyList.addAll(validHistory)

        HistoryStorage.saveHistory(context, validHistory)
    }

    fun addHistory(
        context: Context,
        history: DetectionHistory
    ) {
        historyList.add(0, history)
        HistoryStorage.saveHistory(context, historyList)
    }
}