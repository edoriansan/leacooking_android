package com.android.leacooking.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.android.leacooking.data.model.room.RecipePartIngredient

@Dao
interface RecipePartIngredientDao {
    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(recipePartIngredients: List<RecipePartIngredient>)

    @Query("SELECT count(*) FROM recipe_part_ingredient")
    suspend fun countRecipePartIngredients(): Int

    @Query("SELECT * FROM recipe_part_ingredient")
    fun getAllRecipePartIngredients(): List<RecipePartIngredient>
}
