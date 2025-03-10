package com.android.leacooking.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.android.leacooking.data.model.custom.FullRecipe
import com.android.leacooking.data.model.room.Recipe
import com.android.leacooking.data.model.room.RecipeCategory

@Dao
interface RecipeDao {
    @Transaction
    @Query("""
        SELECT r.*, rp.*, rpi.*, i.*, qt.label as quantityType
        FROM recipe r
        JOIN recipe_part rp ON r.id = rp.id_recipe
        JOIN recipe_part_ingredient rpi ON rp.id = rpi.id_recipe_part
        JOIN ingredient i ON rpi.id_ingredient = i.id
        JOIN quantity_type qt ON i.id_quantity_type = qt.id
        WHERE r.id = :recipeId
    """)
    suspend fun getFullRecipe(recipeId: Long): FullRecipe

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(recipe: List<Recipe>)
}

