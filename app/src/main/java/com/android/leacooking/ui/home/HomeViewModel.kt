package com.android.leacooking.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.leacooking.data.dao.RecipeCategoryDao
import com.android.leacooking.data.model.room.RecipeCategory
import com.android.leacooking.data.repository.SynchronizationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val categoryDao: RecipeCategoryDao,
    private val synchronizationRepository: SynchronizationRepository
) : ViewModel() {
    private val _categories = MutableStateFlow<List<RecipeCategory>>(emptyList())
    private var isSyncInProgress = MutableStateFlow(false)
    val categories: StateFlow<List<RecipeCategory>> = _categories
    var showSyncProgressDialog = MutableStateFlow<Boolean>(false)

    init {
        viewModelScope.launch {
            categoryDao
                .getAllRecipeCategories()
                .collect { categoriesList ->
                    _categories.value = categoriesList
                }
        }
    }

    fun synchronisation() {
        if (isSyncInProgress.value) return
        viewModelScope.launch {
            try {
                isSyncInProgress.value = true
                setShowSyncProgressDialog(true)
                synchronizationRepository.syncAllData()
            } catch (e: Exception) {
                Log.e("Sync", "Erreur de synchronisation: ${e.message}")
            } finally {
                isSyncInProgress.value = false
                setShowSyncProgressDialog(false)
            }
        }
    }

    fun setShowSyncProgressDialog(value: Boolean) {
        showSyncProgressDialog.value = value
    }
}
