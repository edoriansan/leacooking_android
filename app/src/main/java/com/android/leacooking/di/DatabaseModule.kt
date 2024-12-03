package com.android.leacooking.data.di

import android.content.Context
import androidx.room.Room
import com.android.leacooking.data.LeaCookingDatabase
import com.android.leacooking.data.dao.CategoryDao
import com.android.leacooking.data.dao.RecipeDao
import com.android.leacooking.data.dao.RecipePartDao
import com.android.leacooking.data.dao.IngredientDao
import com.android.leacooking.data.dao.RecipePartIngredientDao
import com.android.leacooking.data.dao.SubcategoryDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideLeaCookingDatabase(context: Context): LeaCookingDatabase {
        return LeaCookingDatabase.getDatabase(context)
    }

    @Provides
    fun provideRecipeDao(database: LeaCookingDatabase): RecipeDao {
        return database.recipeDao()
    }

    @Provides
    fun provideRecipePartDao(database: LeaCookingDatabase): RecipePartDao {
        return database.recipePartDao()
    }

    @Provides
    fun provideIngredientDao(database: LeaCookingDatabase): IngredientDao {
        return database.ingredientDao()
    }

    @Provides
    fun provideRecipePartIngredientDao(database: LeaCookingDatabase): RecipePartIngredientDao {
        return database.recipePartIngredientDao()
    }

    @Provides
    fun provideCategoryDao(database: LeaCookingDatabase): CategoryDao {
        return database.categoryDao()
    }

    @Provides
    fun provideSubCategoryDao(database: LeaCookingDatabase): SubcategoryDao {
        return database.subCategoryDao()
    }
}
