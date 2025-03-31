package com.android.leacooking.data.di

import android.content.Context
import com.android.leacooking.data.LeaCookingDatabase
import com.android.leacooking.data.dao.IngredientDao
import com.android.leacooking.data.dao.RecipeCategoryDao
import com.android.leacooking.data.dao.RecipeDao
import com.android.leacooking.data.dao.RecipePartDao
import com.android.leacooking.data.dao.RecipePartIngredientDao
import com.android.leacooking.data.dao.RecipeSubcategoryDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun provideContext(@ApplicationContext application: Context): Context {
        return application
    }

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
    fun provideRecipeCategoryDao(database: LeaCookingDatabase): RecipeCategoryDao {
        return database.categoryDao()
    }

    @Provides
    fun provideRecipeSubcategoryDao(database: LeaCookingDatabase): RecipeSubcategoryDao {
        return database.recipeRecipeSubcategoryDao()
    }
}
