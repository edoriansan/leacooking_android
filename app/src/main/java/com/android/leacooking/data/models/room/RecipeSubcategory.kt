package com.android.leacooking.data.models.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity
data class RecipeSubcategory(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Long,

    @ColumnInfo(name = "sub_category_label")
    val recipeSubcategoryLabel: String,

    @ColumnInfo(name = "sub_category_img")
    val recipeSubcategoryImg: String,

    @ColumnInfo(name = "id_category")
    val categoryId: Long,

    val categoryLabel: String
)
