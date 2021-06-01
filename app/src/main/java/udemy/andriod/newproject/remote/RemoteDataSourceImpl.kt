package udemy.andriod.newproject.remote

import udemy.andriod.newproject.repository.service.roomdatabase.APIRequestUser
import udemy.andriod.newproject.repository.service.model.UsersJsonItem

class RemoteDataSourceImpl(private val service: APIRequestUser): RemoteDataSource {

    override suspend fun getUsers(since: Int): List<UsersJsonItem> = service.getUsers(since)

    override suspend fun getUser(login: String):UsersJsonItem = service.getUser(login)
}