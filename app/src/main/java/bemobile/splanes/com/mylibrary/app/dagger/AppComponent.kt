package bemobile.splanes.com.mylibrary.app.dagger

import bemobile.splanes.com.mylibrary.app.App
import dagger.Component

@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(app: App)
}