package com.myapp.millionaire.ui.base

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
    }
}