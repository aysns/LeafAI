package com.example.leafai.history

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object HistoryStorage {

    private const val PREF_NAME = "leafyai_history"
    private const val KEY_HISTORY = "history_list"

    fun saveHistory(
        context: Context,
        historyList: List<DetectionHistory>
    ) {
        val prefs = context.getSharedPreferences(
            PREF_NAME,
            Context.MODE_PRIVATE
        )

        val json = Gson().toJson(historyList)

        prefs.edit()
            .putString(KEY_HISTORY, json)
            .apply()
    }

    fun loadHistory(context: Context): MutableList<DetectionHistory> {
        val prefs = context.getSharedPreferences(
            PREF_NAME,
            Context.MODE_PRIVATE
        )

        val json = prefs.getString(KEY_HISTORY, null)
            ?: return mutableListOf()

        val type = object : TypeToken<MutableList<DetectionHistory>>() {}.type

        return Gson().fromJson(json, type)
    }

    fun removeExpiredHistory(
        historyList: MutableList<DetectionHistory>
    ): MutableList<DetectionHistory> {

        val fourteenDays =
            14L * 24 * 60 * 60 * 1000

        val now = System.currentTimeMillis()

        return historyList.filter {
            now - it.timestamp <= fourteenDays
        }.toMutableList()
    }
}