package com.android.leacooking.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.android.leacooking.data.models.room.RecipePart

@Dao
interface RecipePartDao {
    @Dao
    interface RecipePartDao {
        @Transaction
        @Query("SELECT * FROM recipe_part WHERE id_recipe = :recipeId")
        suspend fun getRecipePartsWithIngredients(recipeId: Long): List<RecipePart>
    }
}
