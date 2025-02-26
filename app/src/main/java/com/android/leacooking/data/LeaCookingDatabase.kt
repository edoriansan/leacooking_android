package com.android.leacooking.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.android.leacooking.data.dao.IngredientDao
import com.android.leacooking.data.dao.QuantityTypeDao
import com.android.leacooking.data.dao.RecipeDao
import com.android.leacooking.data.dao.RecipePartDao
import com.android.leacooking.data.dao.RecipePartIngredientDao
import com.android.leacooking.data.dao.RecipeCategoryDao
import com.android.leacooking.data.dao.RecipeSubcategoryDao
import com.android.leacooking.data.models.room.Recipe
import com.android.leacooking.data.models.room.RecipeCategory
import com.android.leacooking.data.models.room.Ingredient
import com.android.leacooking.data.models.room.QuantityType
import com.android.leacooking.data.models.room.RecipePart
import com.android.leacooking.data.models.room.RecipePartIngredient
import com.android.leacooking.data.models.room.RecipeSubcategory

/**
 * Database class with a singleton Instance object.
 */
@Database(
    entities = [
        Recipe::class,
        RecipePart::class,
        Ingredient::class,
        RecipePartIngredient::class,
        RecipeCategory::class,
        RecipeSubcategory::class,
        QuantityType::class
    ],
    version = 1,
    exportSchema = false
)
abstract class LeaCookingDatabase : RoomDatabase() {

    abstract fun recipeDao(): RecipeDao
    abstract fun recipePartDao(): RecipePartDao
    abstract fun ingredientDao(): IngredientDao
    abstract fun quantityTypeDao(): QuantityTypeDao
    abstract fun recipePartIngredientDao(): RecipePartIngredientDao
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
