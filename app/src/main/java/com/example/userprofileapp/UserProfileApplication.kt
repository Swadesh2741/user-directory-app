package com.example.userprofileapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class UserProfileApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}