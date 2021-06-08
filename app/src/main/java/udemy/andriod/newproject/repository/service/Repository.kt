package udemy.andriod.newproject.repository.service

import retrofit2.http.Path
import udemy.andriod.newproject.repository.service.roomdatabase.Bio
import udemy.andriod.newproject.repository.service.model.UsersJsonItem

interface Repository {
    suspend fun getUsers(since: Int): List<UsersJsonItem>

    suspend fun getUser(login: String): UsersJsonItem?

   fun insertUsers(users :List<UsersJsonItem>)

    fun saveUser(user : UsersJsonItem)

    fun getUserFormDb(login: String): UsersJsonItem?

    fun updateUserBio(userBio: Bio)

    fun insertBio(bio: Bio)

    fun getBio(id: Int): Bio?

    fun searchUserByName(search: String): List<UsersJsonItem>



}