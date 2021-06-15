package udemy.andriod.newproject

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import udemy.andriod.newproject.modules.*

class StartKoin: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
        androidContext(this@StartKoin)
            modules(dataSourceModule)
            modules(repositoryModule)
            modules(networkModule)
            modules(viewModelModule)
            modules(dataBaseModule)
            modules(viewModelModuleUserInfo)
        }
    }
}