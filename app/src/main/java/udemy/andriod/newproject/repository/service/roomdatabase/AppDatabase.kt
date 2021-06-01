package udemy.andriod.newproject.repository.service.roomdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import udemy.andriod.newproject.repository.service.model.UsersJsonItem

@Database(entities = [UsersJsonItem::class, Bio::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun bioDao(): BioDao



    companion object {
        @Volatile
        var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context) : AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext, AppDatabase::class.java, "AppDatabase"
                ).allowMainThreadQueries().build()
                INSTANCE = instance
                instance
            }
        }
    }

}