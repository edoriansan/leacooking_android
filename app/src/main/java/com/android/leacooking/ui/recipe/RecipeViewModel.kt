package com.android.leacooking.ui.recipe

import androidx.lifecycle.ViewModel
import com.android.leacooking.data.models.room.Recipe
import com.android.leacooking.data.models.room.RecipePart
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import kotlin.collections.filter

@HiltViewModel
class RecipeViewModel @Inject constructor() : ViewModel() {
    private val recipeItems = listOf(
        Recipe(
            id = 1,
            title = "Poulet rôti",
            imageUrl = "https://www.apero-bordeaux.fr/wp-content/uploads/2024/02/20240216_65cfa1ce1fa54.jpg",
            persons = 6,
            recipeSubcategoryId = 1
        ),
        Recipe(
            id = 2,
            title = "Boulettes de bœuf scandinaves",
            imageUrl = "https://www.demotivateur.fr/uploads/4483/poisson%20bbq%20-%20copie.jpg",
            persons = 6,
            recipeSubcategoryId = 1
        ),
        Recipe(
            id = 3,
            title = "Saumon grillé à l'aneth",
            imageUrl = "https://www.seafoodsource.com/sites/default/files/styles/large/public/2020-03/Salmon-grilled_1.jpg",
            persons = 6,
            recipeSubcategoryId = 2
        ),
        Recipe(
            id = 4,
            title = "Filets de morue au beurre citronné",
            imageUrl = "https://www.seriouseats.com/thmb/qfD6OQ0it0a5HTlQIdw6FgyC57g=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/20160303-lemons-are-the-secret-to-amazing-fish-3x2-5fa52bff4c1a49089be1fe0c3932e8eb.jpg",
            persons = 6,
            recipeSubcategoryId = 2
        )
    )

    private val recipePartsItems = listOf(
        RecipePart(
            id = 1,
            recipeId = 1,
            recipePartTitle = "Préparation du poulet",
            recipePartProcess = "Lavez le poulet, salez-le et poivrez-le, puis laissez-le reposer pendant 30 minutes."
        ),
        RecipePart(
            id = 2,
            recipeId = 1,
            recipePartTitle = "Cuisson du poulet",
            recipePartProcess = "Préchauffez le four à 180°C, puis enfournez le poulet pendant 1h30."
        ),
        RecipePart(
            id = 3,
            recipeId = 2,
            recipePartTitle = "Préparation des boulettes",
            recipePartProcess = "Mélangez la viande hachée avec des épices, façonnez des boulettes, et laissez-les reposer."
        ),
        RecipePart(
            id = 4,
            recipeId = 2,
            recipePartTitle = "Cuisson des boulettes",
            recipePartProcess = "Faites cuire les boulettes à feu moyen dans une poêle avec un peu d'huile."
        ),
        RecipePart(
            id = 5,
            recipeId = 3,
            recipePartTitle = "Préparation du saumon",
            recipePartProcess = "Assaisonnez le saumon avec de l'aneth, du sel et du poivre."
        ),
        RecipePart(
            id = 6,
            recipeId = 3,
            recipePartTitle = "Cuisson du saumon",
            recipePartProcess = "Grillez le saumon à feu moyen pendant environ 5 minutes de chaque côté."
        ),
        RecipePart(
            id = 7,
            recipeId = 4,
            recipePartTitle = "Préparation des filets",
            recipePartProcess = "Assaisonnez les filets avec du sel, du poivre et du jus de citron."
        ),
        RecipePart(
            id = 8,
            recipeId = 4,
            recipePartTitle = "Cuisson des filets",
            recipePartProcess = "Faites cuire les filets dans une poêle avec du beurre à feu moyen jusqu'à ce qu'ils soient dorés."
        )
    )

    fun loadRecipeParts(recipeId: Long) {
        _recipeParts.value = recipePartsItems.filter { it.recipeId == recipeId }
    }

    fun loadRecipe(recipeId: Long) {
        _recipe.value = recipeItems.firstOrNull { it.id == recipeId }
            ?: Recipe(id = -1, title = "Recette non trouvée", imageUrl = "", persons = 0, recipeSubcategoryId = 0)
    }

    private val _recipe = MutableStateFlow<Recipe>(recipeItems.elementAt(1))
    val recipe: StateFlow<Recipe> get() = _recipe

    private val _recipeParts = MutableStateFlow<List<RecipePart>>(recipePartsItems)
    val recipeParts: StateFlow<List<RecipePart>> get() = _recipeParts
}
