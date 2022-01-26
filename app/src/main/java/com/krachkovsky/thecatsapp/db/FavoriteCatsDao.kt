package com.krachkovsky.thecatsapp.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.krachkovsky.thecatsapp.models.AnyCat

@Dao
interface FavoriteCatsDao {

    @Query("SELECT * FROM favorite_cats")
    fun getAll(): List<AnyCat>

    @Insert(onConflict = REPLACE)
    fun insertCat(cat: AnyCat)

    @Delete
    fun deleteCat(cat: AnyCat)
}