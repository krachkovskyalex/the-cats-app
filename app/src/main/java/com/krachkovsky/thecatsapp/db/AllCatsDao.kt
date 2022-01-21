package com.krachkovsky.thecatsapp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.krachkovsky.thecatsapp.models.AnyCat

@Dao
interface AllCatsDao {

    @Query("SELECT * FROM all_cats")
    fun getAll(): LiveData<List<AnyCat>>

    @Query("SELECT * FROM all_cats WHERE id IN (:catsIds)")
    fun getAllByIds(catsIds: List<String>): LiveData<List<AnyCat>>

    @Insert(onConflict = REPLACE)
    fun insertAllCats(cats: List<AnyCat>)

    @Delete
    fun deleteCat(cat: AnyCat)
}