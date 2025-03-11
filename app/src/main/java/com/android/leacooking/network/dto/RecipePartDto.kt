package com.android.leacooking.network.dto

import com.squareup.moshi.Json

data class RecipePartDto(
    @Json(name = "id") val id: Long,
    @Json(name = "recipePartTitle") val recipePartTitle: String,
    @Json(name = "recipePartProcess") val recipePartProcess: String,
    @Json(name = "recipeId") val recipeId: Long,
    @Json(name = "recipePartIngredients") val recipePartIngredients: List<RecipePartIngredientDto>
)
