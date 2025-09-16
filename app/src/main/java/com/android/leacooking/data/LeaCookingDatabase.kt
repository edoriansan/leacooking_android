package com.android.leacooking.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.android.leacooking.data.dao.IngredientDao
import com.android.leacooking.data.dao.RecipeDao
import com.android.leacooking.data.dao.RecipeCategoryDao
import com.android.leacooking.data.dao.RecipeIngredientDao
import com.android.leacooking.data.dao.RecipeSubcategoryDao
import com.android.leacooking.data.model.custom.IngredientWithQuantity
import com.android.leacooking.data.model.room.Recipe
import com.android.leacooking.data.model.room.RecipeCategory
import com.android.leacooking.data.model.room.Ingredient
import com.android.leacooking.data.model.room.RecipeIngredient
import com.android.leacooking.data.model.room.RecipeSubcategory

/**
 * Database class with a singleton Instance object.
 */
@Database(
    entities = [
        Recipe::class,
        Ingredient::class,
        RecipeIngredient::class,
        RecipeCategory::class,
        RecipeSubcategory::class
    ],
    views = [
        IngredientWithQuantity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class LeaCookingDatabase : RoomDatabase() {

    abstract fun recipeDao(): RecipeDao
    abstract fun ingredientDao(): IngredientDao
    abstract fun recipeIngredientDao(): RecipeIngredientDao
    abstract fun categoryDao(): RecipeCategoryDao
    abstract fun recipeRecipeSubcategoryDao(): RecipeSubcategoryDao

    companion object {
        @Volatile
        private var INSTANCE: LeaCookingDatabase? = null

        fun getDatabase(context: Context): LeaCookingDatabase {
            return INSTANCE ?: synchronized(this) {
            Room.databaseBuilder(context, LeaCookingDatabase::class.java, "lea_cooking_db")
                .fallbackToDestructiveMigration()
                .build()
                .also { INSTANCE = it }
            }
        }
    }
}
