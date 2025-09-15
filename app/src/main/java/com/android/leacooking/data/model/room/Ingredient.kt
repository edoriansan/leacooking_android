package com.android.leacooking.data.model.room

import androidx.room.*

@Entity(tableName = "ingredient")
data class Ingredient(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Long,

    @ColumnInfo(name = "ingredient_label")
    val label: String
)
