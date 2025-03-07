package com.android.leacooking.network.dto

import com.squareup.moshi.Json

data class RecipeDto(
    @Json(name = "id") val id: Long,
    @Json(name = "title") val title: String,
    @Json(name = "image_url") val imageUrl: String,
    @Json(name = "persons") val persons: Int,
    @Json(name = "recipe_subcategory_id") val recipeSubcategoryId: Long
)
