package com.android.leacooking.data.repository

import android.util.Log
import com.android.leacooking.data.dao.IngredientDao
import com.android.leacooking.data.dao.RecipeCategoryDao
import com.android.leacooking.data.dao.RecipeDao
import com.android.leacooking.data.dao.RecipeSubcategoryDao
import com.android.leacooking.data.mapper.IngredientMapper
import com.android.leacooking.data.mapper.RecipeCategoryMapper
import com.android.leacooking.data.mapper.RecipeMapper
import com.android.leacooking.data.mapper.RecipePartIngredientMapper
import com.android.leacooking.data.mapper.RecipePartMapper
import com.android.leacooking.data.mapper.RecipeSubcategoryMapper
import com.android.leacooking.network.service.ApiService
import javax.inject.Inject

class SynchronizationRepository @Inject constructor(
    private val ingredientDao: IngredientDao,
    private val recipeDao: RecipeDao,
    private val categoryDao: RecipeCategoryDao,
    private val subcategoryDao: RecipeSubcategoryDao,
    private val apiService: ApiService
) {

    private suspend fun syncIngredients() {
        try {
            val response = apiService.getIngredients()
            if (response.isSuccessful) {
                val ingredientsFromApi = response.body() ?: emptyList()
                val mappedIngredients = IngredientMapper.mapToEntities(ingredientsFromApi)
                ingredientDao.insertAll(mappedIngredients)
                Log.d("Sync", "Ingredients sync : ${mappedIngredients.size} items")
            } else {
                Log.e("Sync", "Failed to sync ingredients: HTTP ${response.code()} - ${response.message()}")
            }
        } catch (e: Exception) {
            Log.e("Sync", "Failed to sync ingredients: ${e.message}")
        }
    }

    private suspend fun syncCategories() {
        try {
            val response = apiService.getCategories()
            if (response.isSuccessful) {
                val categoriesFromApi = response.body() ?: emptyList()
                val mappedCategories = RecipeCategoryMapper.mapToEntities(categoriesFromApi)
                categoryDao.insertAll(mappedCategories)
                Log.d("Sync", "Categories sync : ${mappedCategories.size} items")
            } else {
                Log.e("Sync", "Failed to sync categories: HTTP ${response.code()} - ${response.message()}")
            }
        } catch (e: Exception) {
            Log.e("Sync", "Failed to sync categories: ${e.message}")
        }
    }

    private suspend fun syncSubcategories() {
        try {
            val response = apiService.getSubcategories()
            if (response.isSuccessful) {
                val subcategoriesFromApi = response.body() ?: emptyList()
                val mappedSubcategories = RecipeSubcategoryMapper.mapToEntities(subcategoriesFromApi)
                subcategoryDao.insertAll(mappedSubcategories)
                Log.d("Sync", "Subcategories sync : ${mappedSubcategories.size} items")
            } else {
                Log.e("Sync", "Failed to sync subcategories: HTTP ${response.code()} - ${response.message()}")
            }
        } catch (e: Exception) {
            Log.e("Sync", "Failed to sync subcategories: ${e.message}")
        }
    }

    suspend fun syncRecipes() {
        try {
            val response = apiService.getRecipes()
            if (response.isSuccessful) {
                val recipesFromApi = response.body() ?: emptyList()
                val recipeEntities = RecipeMapper.mapToEntities(recipesFromApi)
                val recipePartEntities = recipesFromApi.flatMap { recipe ->
                    recipe.recipeParts.map { RecipePartMapper.mapTo(it) }
                }
                val recipePartIngredientEntities = recipesFromApi.flatMap { recipe ->
                    recipe.recipeParts.flatMap { part ->
                        part.recipePartIngredients.map { RecipePartIngredientMapper.mapTo(it) }
                    }
                }
                recipeDao.insertRecipesWithPartsAndIngredients(recipeEntities, recipePartEntities, recipePartIngredientEntities)
                Log.d("Sync", "Recipes sync: ${recipesFromApi.size} recipes")
            } else {
                Log.e("Sync", "Failed to sync recipes: HTTP ${response.code()} - ${response.message()}")
            }
        } catch (e: Exception) {
            Log.e("Sync", "Failed to sync recipes: ${e.message}")
        }
    }

    suspend fun syncAllData() {
        syncCategories()
        syncSubcategories()
        syncIngredients()
        syncRecipes()
    }
}
