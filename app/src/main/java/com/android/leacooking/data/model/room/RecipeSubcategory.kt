package com.android.leacooking.data.model.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipe_subcategory")
data class RecipeSubcategory(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Long,

    @ColumnInfo(name = "sub_category_label")
    val recipeSubcategoryLabel: String,

    @ColumnInfo(name = "sub_category_img")
    val recipeSubcategoryImg: String,

    @ColumnInfo(name = "id_category")
    val recipeCategoryId: Long,
)
