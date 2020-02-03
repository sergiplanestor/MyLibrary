package bemobile.splanes.com.mylibrary.app

import android.app.Application
import bemobile.splanes.com.mylibrary.app.koins.appModule
import bemobile.splanes.com.mylibrary.app.koins.networkModule
import org.koin.android.ext.android.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin(androidContext = this, modules = listOf(appModule, networkModule))
    }
}