package udemy.andriod.newproject.repository.service.roomdatabase

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Bio")
data class Bio(
        @PrimaryKey val id: Int,
        var bio: String?

        )