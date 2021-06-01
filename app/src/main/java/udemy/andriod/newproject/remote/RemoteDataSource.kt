package udemy.andriod.newproject.remote

import retrofit2.Call
import retrofit2.http.Path
import udemy.andriod.newproject.repository.service.model.UsersJson
import udemy.andriod.newproject.repository.service.model.UsersJsonItem

interface RemoteDataSource {

        suspend fun getUsers(since: Int) : List<UsersJsonItem>

        suspend fun getUser(login: String): UsersJsonItem
}