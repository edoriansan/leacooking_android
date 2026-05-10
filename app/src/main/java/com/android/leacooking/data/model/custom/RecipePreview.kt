package com.android.leacooking.data.model.custom

    data class RecipePreview(
        val id: Long = 0,
        val title: String,
        val imageUrl: String?,
        val recipeSubcategoryId: Long
    )
