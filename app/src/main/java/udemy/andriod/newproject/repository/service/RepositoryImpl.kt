package udemy.andriod.newproject.repository.service

import udemy.andriod.newproject.repository.service.roomdatabase.Bio
import udemy.andriod.newproject.remote.RemoteDataSource
import udemy.andriod.newproject.repository.service.model.UsersJsonItem
import udemy.andriod.newproject.repository.service.roomdatabase.AppDatabase

class RepositoryImpl(private val remoteDataSource: RemoteDataSource, private val database: AppDatabase): Repository {
//    override suspend fun getUsers() =
//        remoteDataSource.getUsers()

    override suspend fun getUsers(since: Int): List<UsersJsonItem> {
        return remoteDataSource.getUsers(since)
    }

    override suspend fun getUser(login: String): UsersJsonItem {
        return remoteDataSource.getUser(login)
    }

    override fun insertUsers(users: List<UsersJsonItem>) {
        users.forEach { user ->
            database.userDao().insertUser(user)
        }

    }

    override fun saveUser(user: UsersJsonItem) {
        database.userDao().insertUser(user)
    }

    override fun getUserFormDb(login: String) : UsersJsonItem? {
        return database.userDao().getUserByLogin(login)

    }

    override fun updateUserBio(userBio: Bio) {
        database.bioDao().updateBioUser(userBio)
    }

    override fun insertBio(bio: Bio) {
        database.bioDao().insertBio(bio)
    }

    override fun getBio(id: Int): Bio?{
       return database.bioDao().getBio(id)
    }

    override fun searchUserByName(search: String): List<UsersJsonItem> {
        return database.userDao().searchUserByName(search)
    }
}

