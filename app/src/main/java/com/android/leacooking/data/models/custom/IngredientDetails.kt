package com.android.leacooking.data.models.custom

import androidx.room.ColumnInfo

data class IngredientDetails(
    @ColumnInfo(name = "ingredientLabel")
    val ingredientLabel: String,

    @ColumnInfo(name = "quantity")
    val quantity: String,

    @ColumnInfo(name = "unit")
    val unit: String?
)
