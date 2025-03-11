package com.android.leacooking.data.model.room

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
    tableName = "recipe_part_ingredient",
    primaryKeys = ["id_recipe_part", "id_ingredient"]
)
data class RecipePartIngredient(
    @ColumnInfo(name = "id_recipe_part")
    val recipePartId: Long,

    @ColumnInfo(name = "id_ingredient")
    val ingredientId: Long,

    @ColumnInfo(name = "quantity")
    val quantity: Int
)
