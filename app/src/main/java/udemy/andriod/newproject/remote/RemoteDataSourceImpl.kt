package udemy.andriod.newproject.remote

import retrofit2.HttpException
import udemy.andriod.newproject.repository.service.roomdatabase.APIRequestUser
import udemy.andriod.newproject.repository.service.model.UsersJsonItem

class RemoteDataSourceImpl(private val service: APIRequestUser): RemoteDataSource {

    override suspend fun getUsers(since: Int): List<UsersJsonItem> {
        var users: List<UsersJsonItem>
        try {
            users = service.getUsers(since)
        }catch (httpEx:HttpException){
            httpEx.printStackTrace()
            users = emptyList()
        }
        return users
    }

    override suspend fun getUser(login: String):UsersJsonItem = service.getUser(login)
}