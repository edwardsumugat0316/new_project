package udemy.andriod.newproject.repository.service.roomdatabase

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import udemy.andriod.newproject.repository.service.model.UsersJsonItem

interface APIRequestUser {
    // users?since=0
    @GET("users")
   suspend fun getUsers(@Query("since") since: Int): List<UsersJsonItem>

    @GET("users/{login}")
   suspend fun getUser(@Path("login") login: String): UsersJsonItem

}