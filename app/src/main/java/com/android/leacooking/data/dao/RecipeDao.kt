package com.android.leacooking.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.android.leacooking.data.model.custom.FullRecipe
import com.android.leacooking.data.model.custom.RecipePreview
import com.android.leacooking.data.model.room.Recipe
import com.android.leacooking.data.model.room.RecipePart
import com.android.leacooking.data.model.room.RecipePartIngredient

@Dao
interface RecipeDao {
    @Query("""
        SELECT r.*, rp.*, rpi.*, i.*, qt.label as quantityType, r.id
        FROM recipe r
        JOIN recipe_part rp ON r.id = rp.id_recipe
        JOIN recipe_part_ingredient rpi ON rp.id = rpi.id_recipe_part
        JOIN ingredient i ON rpi.id_ingredient = i.id
        JOIN quantity_type qt ON i.id_quantity_type = qt.id
        WHERE r.id = :recipeId
    """)
    suspend fun getFullRecipe(recipeId: Long): FullRecipe

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(recipe: List<Recipe>)

    @Query("SELECT count(*) FROM recipe")
    suspend fun countRecipes(): Int

    @Query("SELECT * FROM recipe")
    fun getAllRecipes(): List<Recipe>

    @Query("SELECT id, title, image_url AS imageUrl, id_subcategory AS recipeSubcategoryId FROM recipe WHERE id_subcategory = :recipeSubcategoryId")
    fun getRecipesBySubcategoryId(recipeSubcategoryId: Long): List<RecipePreview>

    @Transaction
    suspend fun insertRecipesWithPartsAndIngredients(
        recipes: List<Recipe>,
        recipeParts: List<RecipePart>,
        recipePartIngredients: List<RecipePartIngredient>
    ) {
        insertAll(recipes)
        insertRecipeParts(recipeParts)
        insertRecipePartIngredients(recipePartIngredients)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipeParts(recipeParts: List<RecipePart>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipePartIngredients(recipePartIngredients: List<RecipePartIngredient>)

    @Query("SELECT * FROM recipe WHERE id = :recipeId")
    suspend fun getRecipeById(recipeId: Long): Recipe
}
