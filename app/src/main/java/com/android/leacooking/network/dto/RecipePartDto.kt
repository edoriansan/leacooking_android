package com.android.leacooking.network.dto

import com.squareup.moshi.Json

data class RecipePartDto(
    @Json(name = "id") val id: Long,
    @Json(name = "recipe_part_title") val recipePartTitle: String,
    @Json(name = "recipe_part_process") val recipePartProcess: String,
    @Json(name = "recipe_id") val recipeId: Long
)
