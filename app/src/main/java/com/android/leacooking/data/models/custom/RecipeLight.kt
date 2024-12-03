package com.android.leacooking.data.models.custom

import androidx.room.ColumnInfo

data class RecipeLight(
    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "image_url")
    val imageUrl: String?
)
