package udemy.andriod.newproject.repository.service.roomdatabase

import androidx.room.*
import udemy.andriod.newproject.repository.service.model.UsersJsonItem

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(users: UsersJsonItem)

    @Query("SELECT * FROM User")
    fun getAll(): UsersJsonItem

    @Query("SELECT * FROM User WHERE login = :login")
    fun getUserByLogin(login: String): UsersJsonItem?

    @Query("SELECT * FROM User WHERE login = :login")
    fun searchUserByLogin(login: String): UsersJsonItem?


}