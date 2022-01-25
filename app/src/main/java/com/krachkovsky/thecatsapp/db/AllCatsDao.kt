package com.krachkovsky.thecatsapp.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.krachkovsky.thecatsapp.models.FavoriteCat

@Dao
interface AllCatsDao {

    @Query("SELECT * FROM favorite_cats")
    fun getAll(): List<FavoriteCat>

    @Insert(onConflict = REPLACE)
    fun insertCat(cat: FavoriteCat)

    @Delete
    fun deleteCat(cat: FavoriteCat)
}