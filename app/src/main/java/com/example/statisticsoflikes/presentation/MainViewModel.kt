package com.example.statisticsoflikes.presentation

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.example.statisticsoflikes.data.preferences.SaveReviewByPreference
import com.example.statisticsoflikes.data.preferences.SaveStatisticByPreferences
import com.example.statisticsoflikes.domain.DisLike
import com.example.statisticsoflikes.domain.Like
import com.example.statisticsoflikes.domain.Statistic
import com.example.statisticsoflikes.untils.Contract.Keys.DISLIKE
import com.example.statisticsoflikes.untils.Contract.Keys.LIKE
import kotlinx.coroutines.flow.MutableStateFlow
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainViewModel(application: Application) : AndroidViewModel(application) {

    val likeState = MutableStateFlow(Like(0, "", 0))
    val disLikeState = MutableStateFlow(DisLike(0, "", 0))

    private val savePreviewByPreferences = SaveReviewByPreference(application, "Review")
    private val saveStatisticByPreferences = SaveStatisticByPreferences(application, "Statistic")

    fun clickBtn(item: String, number: String, text: String) {
        when (item) {
            LIKE -> {
                addLike()
            }

            DISLIKE -> {
                addDisLike(number, text)
            }
        }
    }

    private fun addLike() {
        val oldValue = likeState.value.count
        val newValue = oldValue + 1
        likeState.value =
            likeState.value.copy(date = getCurrentDateAndTime(), count = newValue)
        Log.d("Click BtN", likeState.value.count.toString())
    }

    private fun addDisLike(number: String, text: String) {
        if (number == "" && text == "") {
            val oldValue = disLikeState.value.count
            val newValue = oldValue + 1
            disLikeState.value =
                disLikeState.value.copy(date = getCurrentDateAndTime(), count = newValue)
        } else {
            val oldValue = disLikeState.value.count
            val newValue = oldValue + 1
            disLikeState.value =
                disLikeState.value.copy(date = getCurrentDateAndTime(), count = newValue)
            addReview(number, text)
        }
    }

    fun getStatisticFromPreferences(): List<String> {
        val list: MutableList<String> = mutableListOf()
        val allData: List<Pair<Int, String>> = saveStatisticByPreferences.getAllData()
        for ((_, value) in allData) {
            list.add(value)
        }
        return list
    }


    fun getPreviewFromPreferences(): List<String> {
        val list: MutableList<String> = mutableListOf()
        val allData: List<Pair<Int, String>> = savePreviewByPreferences.getAllData()
        for ((_, value) in allData) {
            list.add(value)
        }
        return list
    }


    fun saveStatisticByDay() {
        val data: String =
            "Дата: ${likeState.value.date} / Количество лайков ${likeState.value.count} / Количество дизлайков: ${disLikeState.value.count}"
        saveStatisticByPreferences.saveData(likeState.value.id, data)
    }


    private fun addReview(number: String, text: String) {
        val statistic = Statistic(date = getCurrentDateAndTime(), number = number, text = text)
        val data: String = "Дата: ${getCurrentDateAndTime()} / Номер: $number / Текст: $text"
        savePreviewByPreferences.saveData(statistic.id, data)
    }

    fun clearAllDataPreferences() {
        saveStatisticByPreferences.clearAllData()
        savePreviewByPreferences.clearAllData()
    }

    private fun getCurrentDateAndTime(): String {
        val dateFormat = SimpleDateFormat("yyyy:MM:dd", Locale.getDefault())
        val date = Date(System.currentTimeMillis())
        return dateFormat.format(date)
    }
}


