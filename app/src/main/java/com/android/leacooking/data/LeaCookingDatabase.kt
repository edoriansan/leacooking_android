package com.android.leacooking.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.android.leacooking.data.converters.DateConverter
import com.android.leacooking.data.dao.IngredientDao
import com.android.leacooking.data.dao.RecipeDao
import com.android.leacooking.data.dao.RecipePartDao
import com.android.leacooking.data.dao.RecipePartIngredientDao
import com.android.leacooking.data.dao.CategoryDao
import com.android.leacooking.data.dao.SubcategoryDao
import com.android.leacooking.data.models.room.Category
import com.android.leacooking.data.models.room.Ingredient
import com.android.leacooking.data.models.room.Recipe
import com.android.leacooking.data.models.room.RecipePart
import com.android.leacooking.data.models.room.RecipePartIngredient
import com.android.leacooking.data.models.room.SubCategory

/**
 * Database class with a singleton Instance object.
 */
@Database(
    entities = [
        Recipe::class,
        RecipePart::class,
        Ingredient::class,
        RecipePartIngredient::class,
        Category::class,
        SubCategory::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(DateConverter::class)
abstract class LeaCookingDatabase : RoomDatabase() {

    abstract fun recipeDao(): RecipeDao
    abstract fun recipePartDao(): RecipePartDao
    abstract fun ingredientDao(): IngredientDao
    abstract fun recipePartIngredientDao(): RecipePartIngredientDao
    abstract fun categoryDao(): CategoryDao
    abstract fun subCategoryDao(): SubcategoryDao


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
