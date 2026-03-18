package com.android.leacooking.ui.subcategories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.leacooking.data.dao.RecipeSubcategoryDao
import com.android.leacooking.data.model.room.RecipeSubcategory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SubCategoriesViewModel @Inject constructor(
    private val recipeSubcategoryDao: RecipeSubcategoryDao
) : ViewModel() {

    private val _subCategories = MutableStateFlow<List<RecipeSubcategory>>(emptyList())
    val subCategories: StateFlow<List<RecipeSubcategory>> get() = _subCategories.asStateFlow()

    fun loadSubCategories(recipeCategoryId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = recipeSubcategoryDao.getSubcategoriesByCategoryId(recipeCategoryId)
            withContext(Dispatchers.Main) {
                _subCategories.value = result
            }
        }
    }
}
