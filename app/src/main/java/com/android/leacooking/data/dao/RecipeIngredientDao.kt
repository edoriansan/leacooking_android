package com.android.leacooking.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.android.leacooking.data.model.room.RecipeIngredient

@Dao
interface RecipeIngredientDao {
    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(recipeIngredients: List<RecipeIngredient>)

    @Query("SELECT count(*) FROM recipe_ingredient")
    suspend fun countRecipeIngredients(): Int

    @Query("SELECT * FROM recipe_ingredient")
    fun getAllRecipeIngredients(): List<RecipeIngredient>
}
