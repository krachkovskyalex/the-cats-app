package com.krachkovsky.thecatsapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.krachkovsky.thecatsapp.util.Constants

@Entity(tableName = Constants.ALL_CATS)
data class AnyCat(
    @PrimaryKey
    val id: String,
    val url: String,
    val width: Int,
    val height: Int
)