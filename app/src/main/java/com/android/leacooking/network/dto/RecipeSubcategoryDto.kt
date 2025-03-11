package com.android.leacooking.network.dto

import com.squareup.moshi.Json

data class RecipeSubcategoryDto(
    @Json(name = "id") val id: Long,
    @Json(name = "recipeSubcategoryLabel") val recipeSubcategoryLabel: String,
    @Json(name = "recipeCategoryId") val recipeCategoryId: Long,
    @Json(name = "recipeSubcategoryImg")  val recipeSubcategoryImg: String
)
