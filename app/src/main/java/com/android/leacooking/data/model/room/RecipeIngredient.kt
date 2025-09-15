package com.android.leacooking.data.model.room

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
    tableName = "recipe_ingredient",
    primaryKeys = ["id_recipe", "id_ingredient"]
)
data class RecipeIngredient(
    @ColumnInfo(name = "id_recipe")
    val recipeId: Long,

    @ColumnInfo(name = "id_ingredient")
    val ingredientId: Long,

    @ColumnInfo(name = "quantity")
    val quantity: String?
)
