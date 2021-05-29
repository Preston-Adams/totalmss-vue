package com.rodyapal.mss.utils

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager
import com.rodyapal.mss.R
import java.util.*

/**
 * @return current system time in milis
 **/
fun getCurrentTime(): Long = Calendar.getInstance().timeInMillis

fun hideKeyboard(activity: Activity) {
    val inputMethodManager = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    val currentFocusedView = activity.currentFocus
    currentFocusedView?.let {
        inputMethodManager.hideSoftInputFromWindow(currentFocusedView.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
    }
}

//TODO: remove hardcode strings
fun getDayIndex(dayName: String) : Int = when (dayName) {
    //According to Calendar.DAY_OF_WEEK
    "понедельник" -> 2
    "вторник" -> 3
    "среда" -> 4
    "четверг" -> 5
    "пятница" -> 6
    "суббота" -> 7
    else -> 1
}

fun String.capital() : String = replaceFirstChar {
    if (it.isLowerCase()) it.titlecase(
        Locale.getDefault()
    ) else it.toString()
}

fun getSubjectType(context: Context, type: String) : String = with(context) {
    return when (type) {
        "пр" -> getString(R.string.lesson_type_practice)
        "лк" -> getString(R.string.lesson_type_lecture)
        "лаб" -> getString(R.string.lesson_type_lab)
        "с/р" -> getString(R.string.lesson_type_self_training)
        else -> type
    }
}