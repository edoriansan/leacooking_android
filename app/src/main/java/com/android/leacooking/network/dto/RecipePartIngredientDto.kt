package com.android.leacooking.network.dto

import com.squareup.moshi.Json

data class RecipePartIngredientDto(
    @Json(name = "recipePartId") val recipePartId: Long,
    @Json(name = "ingredientId") val ingredientId: Long,
    @Json(name = "quantity") val quantity: Int
)
