package com.android.leacooking.ui.recipes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.leacooking.data.dao.RecipeDao
import com.android.leacooking.data.model.custom.RecipePreview
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class RecipesViewModel @Inject constructor(
    private val recipeDao: RecipeDao
) : ViewModel() {

    private val _recipes = MutableStateFlow<List<RecipePreview>>(emptyList())
    val recipes: StateFlow<List<RecipePreview>> = _recipes.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    fun loadRecipes(recipeSubcategoryId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.value = true
            try {
                val result = recipeDao.getRecipesBySubcategoryId(recipeSubcategoryId)
                _recipes.value = result
            } finally {
                _isLoading.value = false
            }
        }
    }
}
