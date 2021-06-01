package udemy.andriod.newproject.modules

import org.koin.dsl.module
import udemy.andriod.newproject.remote.RemoteDataSource
import udemy.andriod.newproject.remote.RemoteDataSourceImpl

val dataSourceModule = module{
    single<RemoteDataSource> {RemoteDataSourceImpl(service = get())}
}