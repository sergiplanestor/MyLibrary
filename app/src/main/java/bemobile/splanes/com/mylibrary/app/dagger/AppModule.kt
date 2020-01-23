package bemobile.splanes.com.mylibrary.app.dagger

import bemobile.splanes.com.mylibrary.app.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val mApplication: App) {

    @Provides
    @Singleton
    fun providesApplication() = mApplication
}