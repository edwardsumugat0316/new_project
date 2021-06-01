package udemy.andriod.newproject


import org.koin.dsl.module
import udemy.andriod.newproject.repository.service.Repository
import udemy.andriod.newproject.repository.service.RepositoryImpl



val repositoryModule = module {
    single<Repository> {RepositoryImpl(remoteDataSource = get(), database = get()) }
}