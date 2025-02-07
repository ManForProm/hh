package com.yahorhous.hh

import android.app.Application
import com.yahorhous.core.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class HHApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@HHApp)
            modules(
                appModules
            )
        }
    }
}