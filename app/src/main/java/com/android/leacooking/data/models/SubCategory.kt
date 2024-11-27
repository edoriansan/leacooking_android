package com.android.leacooking.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "planning")
data class SubCategory(
    @PrimaryKey val id: Int,
    val subCategoryLabel: String,
    val subCategoryImg: String,
    val categoryLabel: String // temporary
)