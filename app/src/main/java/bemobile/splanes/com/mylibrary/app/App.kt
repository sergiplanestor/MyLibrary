package bemobile.splanes.com.mylibrary.app

import android.app.Application
import bemobile.splanes.com.mylibrary.app.dagger.AppComponent
import bemobile.splanes.com.mylibrary.app.dagger.AppModule
import bemobile.splanes.com.mylibrary.app.dagger.DaggerAppComponent

class App : Application() {

    val component: AppComponent by lazy {
        DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }

    override fun onCreate() {
        super.onCreate()
        component.inject(this)
    }

}