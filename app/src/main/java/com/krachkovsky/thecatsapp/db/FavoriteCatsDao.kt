package com.krachkovsky.thecatsapp.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.krachkovsky.thecatsapp.models.FavoriteCatId

@Dao
interface FavoriteCatsDao {

    @Query("SELECT catId FROM favorite_cats")
    fun getAll(): List<String>

    @Insert(onConflict = REPLACE)
    fun insertCat(cat: FavoriteCatId)

    @Delete
    fun deleteCat(cat: FavoriteCatId)
}