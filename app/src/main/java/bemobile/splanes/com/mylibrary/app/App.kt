package bemobile.splanes.com.mylibrary.app

import android.app.Application
import bemobile.splanes.com.mylibrary.app.koins.*
import org.koin.android.ext.android.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin(
            androidContext = this,
            modules = listOf(
                repositoryModule,
                dataSourceModule,
                useCaseModule,
                networkModule,
                helperModule
            )
        )
    }
}
