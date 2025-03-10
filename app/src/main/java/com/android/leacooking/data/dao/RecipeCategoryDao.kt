package com.android.leacooking.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import com.android.leacooking.data.model.room.RecipeCategory

@Dao
interface RecipeCategoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(recipeCategories: List<RecipeCategory>)

    @Query("SELECT count(*) FROM recipe_category")
    suspend fun countRecipeCategories(): Int

    @Query("SELECT * FROM recipe_category")
    fun getAllRecipeCategories(): Flow<List<RecipeCategory>>
}
