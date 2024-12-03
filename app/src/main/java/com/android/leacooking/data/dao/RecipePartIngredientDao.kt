package com.android.leacooking.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.android.leacooking.data.models.custom.IngredientDetails

@Dao
interface RecipePartIngredientDao {
    @Transaction
    @Query("""
        SELECT 
            i.id AS ingredientId, 
            i.ingredient_label AS ingredientLabel, 
            rpi.quantity, 
            rpi.unit
        FROM recipe_part_ingredient rpi
        JOIN ingredient i ON rpi.id_ingredient = i.id
        WHERE rpi.id_recipe_part = :recipePartId
    """)
    fun getIngredientsForRecipePart(recipePartId: Long): LiveData<List<IngredientDetails>>
}
