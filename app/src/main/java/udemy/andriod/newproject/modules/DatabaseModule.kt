package udemy.andriod.newproject.modules

import org.koin.dsl.module
import udemy.andriod.newproject.remote.RemoteDataSource
import udemy.andriod.newproject.remote.RemoteDataSourceImpl
import udemy.andriod.newproject.repository.service.roomdatabase.AppDatabase

val dataBaseModule = module {
    single<AppDatabase> { AppDatabase.getDatabase(get()) }
}