package com.android.leacooking.ui.home.viewmodel

import androidx.lifecycle.ViewModel
import com.android.leacooking.data.models.room.RecipeCategory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {


    // Mock data
    private val categoryItems = listOf(
        RecipeCategory(
            id = 1,
            categoryLabel = "Salé",
            categoryImg = "https://img.cuisineaz.com/660x660/2016/07/29/i84653-spaghettis-bolognaise-rapides.jpg"
        ),
        RecipeCategory(
            id = 2,
            categoryLabel = "Sucré",
            categoryImg = "https://img-3.journaldesfemmes.fr/H0qYPxS9uCGiAxcOYPriAyleulw=/750x500/3e880207483541898952bc7c3491b00b/ccmcms-jdf/39903996.jpg"
        )
    )
    // End mock data

    private val _categories = MutableStateFlow<List<RecipeCategory>>(categoryItems)
    val categories: StateFlow<List<RecipeCategory>> get() = _categories
}
