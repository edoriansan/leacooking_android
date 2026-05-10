package com.android.leacooking.network.service

import com.android.leacooking.network.dto.IngredientDto
import com.android.leacooking.network.dto.RecipeCategoryDto
import com.android.leacooking.network.dto.RecipeDto
import com.android.leacooking.network.dto.RecipeSubcategoryDto
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("/api/ingredient")
    suspend fun getIngredients(): Response<List<IngredientDto>>

    @GET("/api/recipe")
    suspend fun getRecipes(): Response<List<RecipeDto>>

    @GET("/api/category")
    suspend fun getCategories(): Response<List<RecipeCategoryDto>>

    @GET("/api/subcategory")
    suspend fun getSubcategories(): Response<List<RecipeSubcategoryDto>>
}
