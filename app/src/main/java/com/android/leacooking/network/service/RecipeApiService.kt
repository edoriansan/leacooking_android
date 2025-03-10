package com.android.leacooking.network.service

import com.android.leacooking.network.dto.IngredientDto
import com.android.leacooking.network.dto.QuantityTypeDto
import com.android.leacooking.network.dto.RecipeCategoryDto
import com.android.leacooking.network.dto.RecipeDto
import com.android.leacooking.network.dto.RecipePartDto
import com.android.leacooking.network.dto.RecipePartIngredientDto
import com.android.leacooking.network.dto.RecipeSubcategoryDto
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("/api/ingredient/")
    suspend fun getIngredients(): Response<List<IngredientDto>>

    @GET("/api/quantity-type/")
    suspend fun getQuantityTypes(): Response<List<QuantityTypeDto>>

    @GET("/api/recipes/")
    suspend fun getRecipes(): Response<List<RecipeDto>>

    @GET("/api/category/")
    suspend fun getCategories(): Response<List<RecipeCategoryDto>>

    @GET("/api/subcategory/")
    suspend fun getSubcategories(): Response<List<RecipeSubcategoryDto>>

    @GET("/api/recipe-part/")
    suspend fun getRecipeParts(): Response<List<RecipePartDto>>

    @GET("/api/recipe-part-ingredient/")
    suspend fun getRecipePartIngredients(): Response<List<RecipePartIngredientDto>>
}
