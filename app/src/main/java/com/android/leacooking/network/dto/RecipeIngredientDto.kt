package com.android.leacooking.network.dto

import com.squareup.moshi.Json

data class RecipeIngredientDto(
    @Json(name = "id") val id: Long,
    @Json(name = "recipeId") val recipeId: Long,
    @Json(name = "ingredientId") val ingredientId: Long,
    @Json(name = "quantity") val quantity: String
)
