package com.android.leacooking.data.models.custom

import androidx.room.ColumnInfo

data class RecipeLight(
    @ColumnInfo(name = "id")
    val id: Long = 0,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "image_url")
    val imageUrl: String,

    @ColumnInfo(name = "id_subcategory")
    val subCategoryId: Long
)
