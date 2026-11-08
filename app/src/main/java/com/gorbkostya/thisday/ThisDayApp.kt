package com.gorbkostya.thisday

import android.app.Application
import com.gorbkostya.thisday.di.AppContainer

class ThisDayApp: Application() {
    lateinit var container: AppContainer
        private set

    override fun onCreate() {
        super.onCreate()
        container = AppContainer()
    }
}