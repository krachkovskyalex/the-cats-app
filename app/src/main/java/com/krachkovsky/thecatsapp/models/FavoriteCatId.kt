package com.krachkovsky.thecatsapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.krachkovsky.thecatsapp.util.Constants

@Entity(tableName = Constants.FAVORITE_CATS)
data class FavoriteCatId(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val catId: String
)