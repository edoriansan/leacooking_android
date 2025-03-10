package com.android.leacooking.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.android.leacooking.data.model.room.RecipeSubcategory

@Dao
interface RecipeSubcategoryDao {
    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(recipeSubcategories: List<RecipeSubcategory>)

    @Query("SELECT count(*) FROM recipe_subcategory")
    suspend fun countRecipeSubcategories(): Int

    @Query("SELECT * FROM recipe_subcategory")
    fun getAllRecipeSubcategories(): List<RecipeSubcategory>
}
