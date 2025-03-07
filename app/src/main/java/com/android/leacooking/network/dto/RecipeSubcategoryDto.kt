package com.android.leacooking.network.dto

import com.squareup.moshi.Json

data class RecipeSubcategoryDto(
    @Json(name = "id") val id: Long,
    @Json(name = "label") val label: String,
    @Json(name = "category_id") val categoryId: Long
)
