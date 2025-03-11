package com.android.leacooking.network.dto

import com.squareup.moshi.Json

data class RecipeCategoryDto(
    @Json(name = "id") val id: Long,
    @Json(name = "recipeCategoryLabel") val recipeCategoryLabel: String,
    @Json(name = "recipeCategoryImg") val recipeCategoryImg: String
)
