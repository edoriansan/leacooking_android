package com.android.leacooking.ui.recipe

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.leacooking.data.dao.RecipeDao
import com.android.leacooking.data.model.custom.FullRecipe
import com.android.leacooking.data.model.room.Recipe
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel @Inject constructor(
    private val recipeDao: RecipeDao
) : ViewModel() {

    private val _recipe = MutableStateFlow<FullRecipe>(
        FullRecipe(
            recipe = Recipe(id = -1, title = "Recette non trouvée", recipeImg = "", parts = 0, recipeSubcategoryId = 0, description = ""),
            ingredients = listOf()
        )
    )
    val recipe: StateFlow<FullRecipe> get() = _recipe

    fun loadRecipe(recipeId: Long) {
        viewModelScope.launch {
            try {
                val fullRecipe = recipeDao.getFullRecipe(recipeId)
                Log.d("RecipeViewModel", "FullRecipe: $fullRecipe")
                _recipe.value = fullRecipe
            } catch (e: Exception) {
                _recipe.value = FullRecipe(
                    recipe = Recipe(id = -1, title = "Recette non trouvée", recipeImg = "", parts = 0, recipeSubcategoryId = 0, description = ""),
                    ingredients = listOf()
                )
            }
        }
    }
}

