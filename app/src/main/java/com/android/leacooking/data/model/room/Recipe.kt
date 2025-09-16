package com.android.leacooking.data.model.room

import androidx.room.*

@Entity(tableName = "recipe")
data class Recipe(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Long,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "parts")
    val parts: Int?,

    @ColumnInfo(name = "image_url")
    val recipeImg: String,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "id_subcategory")
    val recipeSubcategoryId: Long
)
