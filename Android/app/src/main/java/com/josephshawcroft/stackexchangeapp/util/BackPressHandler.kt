package com.josephshawcroft.stackexchangeapp.util

import android.app.Activity

interface BackPressHandler {

    fun onBackPressed(parentActivity: Activity)
}