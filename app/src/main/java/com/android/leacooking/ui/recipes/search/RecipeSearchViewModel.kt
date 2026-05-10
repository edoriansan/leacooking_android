package com.android.leacooking.ui.recipes.search

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
import javax.inject.Inject

@HiltViewModel
class RecipeSearchViewModel @Inject constructor(
    private val recipeDao: RecipeDao
) : ViewModel() {

    private val _searchResults = MutableStateFlow<List<RecipePreview>>(emptyList())
    val searchResults: StateFlow<List<RecipePreview>> = _searchResults.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    fun searchRecipes(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.value = true
            try {
                val result = recipeDao.searchRecipesByIngredient(query)
                _searchResults.value = result
            } finally {
                _isLoading.value = false
            }
        }
    }
}
