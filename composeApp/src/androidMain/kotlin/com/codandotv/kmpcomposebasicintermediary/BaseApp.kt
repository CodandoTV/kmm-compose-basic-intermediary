package com.codandotv.kmpcomposebasicintermediary

import android.app.Application
import di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BaseApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@BaseApp)
            modules(appModules)
        }
    }
}