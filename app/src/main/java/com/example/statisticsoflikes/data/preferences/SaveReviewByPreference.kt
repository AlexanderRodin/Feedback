package com.example.statisticsoflikes.data.preferences

import android.content.Context
import android.content.SharedPreferences
import com.example.statisticsoflikes.untils.Contract
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SaveReviewByPreference(private val context: Context, private val category: String) {

    private val PREFS_NAME = "Preference_Review"

    fun saveData(key: Int, value: String) {
        val prefs: SharedPreferences =
            context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = prefs.edit()
        val dataList = getAllData().toMutableList()
        dataList.add(key to value)
        val json = Gson().toJson(dataList)
        editor.putString(category, json)
        editor.apply()
    }

    fun getAllData(): List<Pair<Int, String>> {
        val prefs: SharedPreferences =
            context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val json = prefs.getString(category, null)
        val type = object : TypeToken<List<Pair<Int, String>>>() {}.type

        return if (json != null) {
            Gson().fromJson(json, type)
        } else {
            mutableListOf()
        }
    }

    fun clearAllData() {
        val prefs: SharedPreferences =
            context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = prefs.edit()
        editor.remove(category)
        editor.apply()
    }
}