package com.example.prioritynote.utils

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager


const val DATABASE_VERSION = 1
const val DATABASE_NAME = "priority-note"
const val TABLE_NOTE = "priority_note"

fun hideKeyboard(activity: Activity){
    val inputMethodManager = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

    val currentFocusedView = activity.currentFocus
    currentFocusedView?.let { inputMethodManager.hideSoftInputFromWindow(currentFocusedView.windowToken, InputMethodManager.HIDE_NOT_ALWAYS) }
}