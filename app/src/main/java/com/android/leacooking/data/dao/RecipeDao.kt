package com.android.leacooking.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.android.leacooking.data.models.custom.RecipeLight
import com.android.leacooking.data.models.room.Recipe

@Dao
interface RecipeDao {
    @Query("""
        SELECT r.id, r.title, r.image_url, r.id_subcategory
        FROM recipe r
        WHERE id_subcategory = :subcategoryId
    """)
    fun getRecipeLightBySubcategoryId(subcategoryId: Long): LiveData<List<RecipeLight>>

    @Query("""
        SELECT r.* 
        FROM recipe r
        WHERE r.id = :id
    """)
    fun getRecipeById(id: Long): LiveData<Recipe>
}
