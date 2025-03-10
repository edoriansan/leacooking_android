package com.android.leacooking.data.repository

import android.util.Log
import com.android.leacooking.data.dao.IngredientDao
import com.android.leacooking.data.dao.QuantityTypeDao
import com.android.leacooking.data.dao.RecipeCategoryDao
import com.android.leacooking.data.dao.RecipeDao
import com.android.leacooking.data.dao.RecipePartDao
import com.android.leacooking.data.dao.RecipePartIngredientDao
import com.android.leacooking.data.dao.RecipeSubcategoryDao
import com.android.leacooking.data.mapper.IngredientMapper
import com.android.leacooking.data.mapper.QuantityTypeMapper
import com.android.leacooking.data.mapper.RecipeCategoryMapper
import com.android.leacooking.data.mapper.RecipeMapper
import com.android.leacooking.data.mapper.RecipePartIngredientMapper
import com.android.leacooking.data.mapper.RecipePartMapper
import com.android.leacooking.data.mapper.RecipeSubcategoryMapper
import com.android.leacooking.network.service.ApiService
import javax.inject.Inject

class SynchronizationRepository @Inject constructor(
    private val ingredientDao: IngredientDao,
    private val quantityTypeDao: QuantityTypeDao,
    private val recipeDao: RecipeDao,
    private val categoryDao: RecipeCategoryDao,
    private val subcategoryDao: RecipeSubcategoryDao,
    private val recipePartDao: RecipePartDao,
    private val recipePartIngredientDao: RecipePartIngredientDao,
    private val apiService: ApiService
) {

    private suspend fun syncIngredients() {
        try {
            val response = apiService.getIngredients()
            if (response.isSuccessful) {
                val ingredientsFromApi = response.body() ?: emptyList()
                val ingredientsCount = ingredientDao.countIngredients()

                if (ingredientsCount < ingredientsFromApi.size) {
                    val mappedIngredients = IngredientMapper.mapToEntities(ingredientsFromApi)
                    ingredientDao.insertAll(mappedIngredients)
                    Log.d("Sync", "Ingredients sync : ${mappedIngredients.size} items")
                } else {
                    Log.d("Sync", "Ingredients already up-to-date. No action needed.")
                }
            } else {
                Log.e("Sync", "Failed to sync ingredients: HTTP ${response.code()} - ${response.message()}")
            }
        } catch (e: Exception) {
            Log.e("Sync", "Failed to sync ingredients: ${e.message}")
        }
    }

    private suspend fun syncQuantityTypes() {
        try {
            val response = apiService.getQuantityTypes()
            if (response.isSuccessful) {
                val quantityTypesFromApi = response.body() ?: emptyList()
                val quantityTypesCount = quantityTypeDao.countQuantityTypes()

                if (quantityTypesCount < quantityTypesFromApi.size) {
                    val mappedQuantityTypes = QuantityTypeMapper.mapToEntities(quantityTypesFromApi)
                    quantityTypeDao.insertAll(mappedQuantityTypes)
                    Log.d("Sync", "Quantity types sync : ${mappedQuantityTypes.size} items")
                } else {
                    Log.d("Sync", "Quantity types already up-to-date. No action needed.")
                }
            } else {
                Log.e("Sync", "Failed to sync quantity types: HTTP ${response.code()} - ${response.message()}")
            }
        } catch (e: Exception) {
            Log.e("Sync", "Failed to sync quantity types: ${e.message}")
        }
    }

    private suspend fun syncRecipes() {
        try {
            val response = apiService.getRecipes()
            if (response.isSuccessful) {
                val recipesFromApi = response.body() ?: emptyList()
                val recipesCount = recipeDao.countRecipes()

                if (recipesCount < recipesFromApi.size) {
                    val mappedRecipes = RecipeMapper.mapToEntities(recipesFromApi)
                    recipeDao.insertAll(mappedRecipes)
                    Log.d("Sync", "Recipes sync : ${mappedRecipes.size} items")
                } else {
                    Log.d("Sync", "Recipes already up-to-date. No action needed.")
                }
            } else {
                Log.e("Sync", "Failed to sync recipes: HTTP ${response.code()} - ${response.message()}")
            }
        } catch (e: Exception) {
            Log.e("Sync", "Failed to sync recipes: ${e.message}")
        }
    }

    private suspend fun syncCategories() {
        try {
            val response = apiService.getCategories()
            if (response.isSuccessful) {
                val categoriesFromApi = response.body() ?: emptyList()
                val categoriesCount = categoryDao.countRecipeCategories()

                if (categoriesCount < categoriesFromApi.size) {
                    val mappedCategories = RecipeCategoryMapper.mapToEntities(categoriesFromApi)
                    categoryDao.insertAll(mappedCategories)
                    Log.d("Sync", "Categories sync : ${mappedCategories.size} items")
                } else {
                    Log.d("Sync", "Categories already up-to-date. No action needed.")
                }
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
                val subcategoriesCount = subcategoryDao.countRecipeSubcategories()

                if (subcategoriesCount < subcategoriesFromApi.size) {
                    val mappedSubcategories = RecipeSubcategoryMapper.mapToEntities(subcategoriesFromApi)
                    subcategoryDao.insertAll(mappedSubcategories)
                    Log.d("Sync", "Subcategories sync : ${mappedSubcategories.size} items")
                } else {
                    Log.d("Sync", "Subcategories already up-to-date. No action needed.")
                }
            } else {
                Log.e("Sync", "Failed to sync subcategories: HTTP ${response.code()} - ${response.message()}")
            }
        } catch (e: Exception) {
            Log.e("Sync", "Failed to sync subcategories: ${e.message}")
        }
    }

    private suspend fun syncRecipeParts() {
        try {
            val response = apiService.getRecipeParts()
            if (response.isSuccessful) {
                val recipePartsFromApi = response.body() ?: emptyList()
                val recipePartsCount = recipePartDao.countRecipeParts()

                if (recipePartsCount < recipePartsFromApi.size) {
                    val mappedRecipeParts = RecipePartMapper.mapToEntities(recipePartsFromApi)
                    recipePartDao.insertAll(mappedRecipeParts)
                    Log.d("Sync", "Recipe parts sync : ${mappedRecipeParts.size} items")
                } else {
                    Log.d("Sync", "Recipe parts already up-to-date. No action needed.")
                }
            } else {
                Log.e("Sync", "Failed to sync recipe parts: HTTP ${response.code()} - ${response.message()}")
            }
        } catch (e: Exception) {
            Log.e("Sync", "Failed to sync recipe parts: ${e.message}")
        }
    }

    private suspend fun syncRecipePartIngredients() {
        try {
            val response = apiService.getRecipePartIngredients()
            if (response.isSuccessful) {
                val recipePartIngredientsFromApi = response.body() ?: emptyList()
                val recipePartIngredientsCount = recipePartIngredientDao.countRecipePartIngredients()

                if (recipePartIngredientsCount < recipePartIngredientsFromApi.size) {
                    val mappedRecipePartIngredients = RecipePartIngredientMapper.mapToEntities(recipePartIngredientsFromApi)
                    recipePartIngredientDao.insertAll(mappedRecipePartIngredients)
                    Log.d("Sync", "Recipe part ingredients sync : ${mappedRecipePartIngredients.size} items")
                } else {
                    Log.d("Sync", "Recipe part ingredients already up-to-date. No action needed.")
                }
            } else {
                Log.e("Sync", "Failed to sync recipe part ingredients: HTTP ${response.code()} - ${response.message()}")
            }
        } catch (e: Exception) {
            Log.e("Sync", "Failed to sync recipe part ingredients: ${e.message}")
        }
    }

    suspend fun syncAllData() {
        syncCategories()
        syncSubcategories()
        syncQuantityTypes()
        syncIngredients()
//        syncRecipes()
//        syncRecipeParts()
//        syncRecipePartIngredients()
    }
}

