package com.android.leacooking.data.model.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipe_category")
data class RecipeCategory(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Long,

    @ColumnInfo(name = "category_label")
    val recipeCategoryLabel: String,

    @ColumnInfo(name = "category_img")
    val recipeCategoryImg: String
)
