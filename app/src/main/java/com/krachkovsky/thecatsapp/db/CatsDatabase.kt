package com.krachkovsky.thecatsapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.krachkovsky.thecatsapp.models.AnyCat
import com.krachkovsky.thecatsapp.models.FavoriteCatId
import com.krachkovsky.thecatsapp.util.Constants.DB_NAME

@Database(
    entities = [AnyCat::class, FavoriteCatId::class],
    version = 1,
    exportSchema = false
)
abstract class CatsDatabase : RoomDatabase() {

    companion object {
        private var db: CatsDatabase? = null
        private val LOCK = Any()

        fun getInstance(context: Context): CatsDatabase {
            synchronized(LOCK) {
                db?.let { return it }
                val instance = Room.databaseBuilder(
                    context,
                    CatsDatabase::class.java,
                    DB_NAME
                ).build()
                db = instance
                return instance
            }
        }
    }

    abstract fun favoriteCatsDao(): AllCatsDao
}