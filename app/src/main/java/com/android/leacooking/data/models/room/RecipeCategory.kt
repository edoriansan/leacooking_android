package com.android.leacooking.data.models.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category")
data class RecipeCategory(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Long,

    @ColumnInfo(name = "category_label")
    val categoryLabel: String,

    @ColumnInfo(name = "category_img")
    val categoryImg: String
)
