package com.android.leacooking.ui.recipe

import androidx.lifecycle.ViewModel
import com.android.leacooking.data.models.room.Recipe
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel @Inject constructor() : ViewModel() {
    private val recipeItems = listOf(
        Recipe(
            id = 1,
            title = "Poulet rôti",
            imageUrl = "https://www.legauloisprofessionnel.fr/storage/modelfiles/products/23/11/20/41/image-0-655b725cc2e06.png",
            persons = 6,
            subCategoryId = 1
        ),
        Recipe(
            id = 2,
            title = "Boulettes de bœuf scandinaves",
            imageUrl = "https://www.demotivateur.fr/uploads/4483/poisson%20bbq%20-%20copie.jpg",
            persons = 6,
            subCategoryId = 1
        ),
        Recipe(
            id = 3,
            title = "Saumon grillé à l'aneth",
            imageUrl = "https://www.seafoodsource.com/sites/default/files/styles/large/public/2020-03/Salmon-grilled_1.jpg",
            persons = 6,
            subCategoryId = 2
        ),
        Recipe(
            id = 4,
            title = "Filets de morue au beurre citronné",
            imageUrl = "https://www.seriouseats.com/thmb/qfD6OQ0it0a5HTlQIdw6FgyC57g=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/20160303-lemons-are-the-secret-to-amazing-fish-3x2-5fa52bff4c1a49089be1fe0c3932e8eb.jpg",
            persons = 6,
            subCategoryId = 2
        )
    )

    fun loadRecipe(recipeId: Long) {
        _recipe.value = recipeItems.firstOrNull { it.id == recipeId }
            ?: Recipe(id = -1, title = "Recette non trouvée", imageUrl = "", persons = 0, subCategoryId = 0)
    }

    private val _recipe = MutableStateFlow<Recipe>(recipeItems.elementAt(1))
    val recipe: StateFlow<Recipe> get() = _recipe
}
