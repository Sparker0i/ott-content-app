package me.sparker0i.ottcontent.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import me.sparker0i.ottcontent.model.Country
import me.sparker0i.ottcontent.model.Episode
import me.sparker0i.ottcontent.model.Platform
import me.sparker0i.ottcontent.model.Show
import me.sparker0i.ottcontent.utils.Converters

@Database(
    entities = [Country::class, Episode::class, Platform::class, Show::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class ContentDatabase: RoomDatabase() {
    abstract fun contentDao(): ContentDao

    companion object {
        @Volatile private var instance: ContentDatabase? = null
        private var LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext, ContentDatabase::class.java, "content.db")
                .build()
    }
}