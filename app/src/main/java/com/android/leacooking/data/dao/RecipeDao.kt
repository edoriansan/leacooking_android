package com.android.leacooking.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.android.leacooking.data.models.custom.RecipeLight
import com.android.leacooking.data.models.room.Recipe

@Dao
interface RecipeDao {
    @Query("""
        SELECT r.title, r.image_url 
        FROM recipe r
        WHERE id_subcategory = :subcategoryId
    """)
    fun getRecipeLightBySubcategoryId(subcategoryId: Int): LiveData<List<RecipeLight>>

    @Query("""
        SELECT r.* 
        FROM recipe r
        WHERE id = :id
    """)
    fun getRecipeById(id: Int): LiveData<Recipe>
}
