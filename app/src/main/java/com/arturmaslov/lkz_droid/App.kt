package com.arturmaslov.lkz_droid

import android.app.Application
import android.content.Context

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

    }

    companion object {
        private lateinit var instance: App
        fun getAppContext(): Context {
            return instance.applicationContext
        }
    }

}