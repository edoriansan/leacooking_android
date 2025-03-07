package com.android.leacooking.network.dto

import com.squareup.moshi.Json

data class RecipePartIngredientDto(
    @Json(name = "recipe_part_id") val recipePartId: Long,
    @Json(name = "ingredient_id") val ingredientId: Long,
    @Json(name = "quantity") val quantity: Int
)
