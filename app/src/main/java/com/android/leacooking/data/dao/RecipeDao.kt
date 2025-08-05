package com.android.leacooking.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.android.leacooking.data.model.custom.FullRecipe
import com.android.leacooking.data.model.custom.RecipePreview
import com.android.leacooking.data.model.room.Recipe
import com.android.leacooking.data.model.room.RecipeIngredient

@Dao
interface RecipeDao {
    @Query("""
        SELECT r.*, ri.*, i.*, r.id
        FROM recipe r
        JOIN recipe_ingredient ri ON ri.id_recipe = ri.id_recipe
        JOIN ingredient i ON ri.id_ingredient = i.id
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
        recipeIngredients: List<RecipeIngredient>
    ) {
        insertAll(recipes)
        insertRecipeIngredients(recipeIngredients)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipeIngredients(recipePartIngredients: List<RecipeIngredient>)

    @Query("SELECT * FROM recipe WHERE id = :recipeId")
    suspend fun getRecipeById(recipeId: Long): Recipe

    @Query("""
    SELECT DISTINCT r.id, title, image_url AS imageUrl, id_subcategory AS recipeSubcategoryId 
    FROM recipe r
    JOIN recipe_ingredient ri ON r.id = ri.id_recipe
    JOIN ingredient i ON ri.id_ingredient = i.id
    WHERE LOWER(r.title) LIKE LOWER('%' || :query || '%')
    OR LOWER(i.ingredient_label) LIKE LOWER('%' || :query || '%')
    """)
    suspend fun searchRecipesByIngredient(query: String): List<RecipePreview>

}
