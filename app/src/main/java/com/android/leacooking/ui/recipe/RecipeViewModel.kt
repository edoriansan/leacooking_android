package com.android.leacooking.ui.recipe

import androidx.lifecycle.ViewModel
import com.android.leacooking.data.models.custom.FullRecipe
import com.android.leacooking.data.models.custom.IngredientWithQuantity
import com.android.leacooking.data.models.custom.RecipePartWithIngredients
import com.android.leacooking.data.models.room.Ingredient
import com.android.leacooking.data.models.room.QuantityType
import com.android.leacooking.data.models.room.Recipe
import com.android.leacooking.data.models.room.RecipePart
import com.android.leacooking.data.models.room.RecipePartIngredient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel @Inject constructor() : ViewModel() {
    private val recipeItems = listOf(
        FullRecipe(
            Recipe(
                id = 3,
                title = "Saumon grillé à l'aneth",
                imageUrl = "https://www.seafoodsource.com/sites/default/files/styles/large/public/2020-03/Salmon-grilled_1.jpg",
                persons = 6,
                recipeSubcategoryId = 2
            ),
            parts = listOf(
                RecipePartWithIngredients(
                    part = RecipePart(
                        id = 10L,
                        recipePartTitle = "Pâte à pizza",
                        recipePartProcess = "Mélanger les ingrédients et pétrir la pâte",
                        recipeId = 1L
                    ),
                    ingredients = listOf(
                        IngredientWithQuantity(
                            partIngredient = RecipePartIngredient(
                                recipePartId = 10L,
                                ingredientId = 200L,
                                quantity = 500
                            ),
                            ingredient = Ingredient(
                                id = 200L,
                                label = "Farine",
                                quantityTypeId = 2L
                            ),
                            quantityType = QuantityType(
                                id = 2L,
                                label = "grammes"
                            )
                        ),
                        IngredientWithQuantity(
                            partIngredient = RecipePartIngredient(
                                recipePartId = 10L,
                                ingredientId = 201L,
                                quantity = 1
                            ),
                            ingredient = Ingredient(
                                id = 201L,
                                label = "Œuf",
                                quantityTypeId = 1L
                            ),
                            quantityType = QuantityType(
                                id = 1L,
                                label = "unité"
                            )
                        )
                    )
                )
            )
        )
    )


    fun loadRecipe(recipeId: Long) {
        _recipe.value = recipeItems.firstOrNull { it.recipe.id == recipeId }
            ?: FullRecipe(
                Recipe(id = -1, title = "Recette non trouvée", imageUrl = "", persons = 0, recipeSubcategoryId = 0),
                parts = listOf()
            )
    }

    private val _recipe = MutableStateFlow<FullRecipe>(recipeItems.elementAt(1))
    val recipe: StateFlow<FullRecipe> get() = _recipe
}
