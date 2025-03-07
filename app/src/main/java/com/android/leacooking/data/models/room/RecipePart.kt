package com.android.leacooking.data.models.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipe_part")
data class RecipePart(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Long,

    @ColumnInfo(name = "recipe_part_title")
    val recipePartTitle: String,

    @ColumnInfo(name = "recipe_part_process")
    val recipePartProcess: String,

    @ColumnInfo(name = "id_recipe")
    val recipeId: Long
)
