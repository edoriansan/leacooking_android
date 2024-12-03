package com.android.leacooking.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category")
data class Category(
    @PrimaryKey val id: Int,
    val categoryLabel: String,
    val categoryImg: String
)