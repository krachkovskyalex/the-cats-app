package com.krachkovsky.thecatsapp

import android.app.Application
import com.krachkovsky.thecatsapp.koin.dataModule
import com.krachkovsky.thecatsapp.koin.pageSourceModule
import com.krachkovsky.thecatsapp.koin.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class CatsApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            //            java.lang.NoSuchMethodError: No static method toDouble-impl
            //            Solution from https://github.com/InsertKoinIO/koin/issues/1188
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@CatsApp)
            modules(listOf(dataModule, pageSourceModule, viewModelModule))
        }
    }
}