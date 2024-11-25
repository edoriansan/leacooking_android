package com.android.leacooking.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "planning")
data class Recipe(
    @PrimaryKey val id: Int,
    val recipeLabel: String,
)