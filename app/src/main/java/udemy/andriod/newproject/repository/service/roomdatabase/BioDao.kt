package udemy.andriod.newproject.repository.service.roomdatabase

import androidx.room.*

@Dao
interface BioDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBio(bio: Bio)

    @Update
    fun updateBioUser(userBio: Bio)

    @Query( " Select * FROM Bio WHERE id = :id")
    fun getBio(id: Int): Bio?
}