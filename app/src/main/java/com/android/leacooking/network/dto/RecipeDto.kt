package com.android.leacooking.network.dto

import com.squareup.moshi.Json

data class RecipeDto(
    @Json(name = "id") val id: Long,
    @Json(name = "title") val title: String,
    @Json(name = "persons") val persons: Int,
    @Json(name = "recipeImg") val recipeImg: String,
    @Json(name = "recipeSubcategoryId") val recipeSubcategoryId: Long,
    @Json(name = "recipeIngredients") val recipeIngredients: List<RecipeIngredientDto>
)
