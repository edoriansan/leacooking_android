package com.android.leacooking.ui.recipe

import androidx.lifecycle.ViewModel
import com.android.leacooking.data.model.custom.FullRecipe
import com.android.leacooking.data.model.custom.IngredientWithQuantity
import com.android.leacooking.data.model.custom.RecipePartWithIngredients
import com.android.leacooking.data.model.room.Ingredient
import com.android.leacooking.data.model.room.QuantityType
import com.android.leacooking.data.model.room.Recipe
import com.android.leacooking.data.model.room.RecipePart
import com.android.leacooking.data.model.room.RecipePartIngredient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel @Inject constructor() : ViewModel() {
    private val recipeItems = listOf(
        FullRecipe(
            Recipe(
                id = 1,
                title = "Pizza Margherita",
                imageUrl = "https://fr.ooni.com/cdn/shop/articles/Margherita-9920.jpg?crop=center&height=915&v=1644590066&width=1200",

                persons = 4,
                recipeSubcategoryId = 1
            ),
            parts = listOf(
                RecipePartWithIngredients(
                    part = RecipePart(
                        id = 10L,
                        recipePartTitle = "Préparation de la pâte",
                        recipePartProcess = "Mélanger les ingrédients, laisser reposer la pâte et étaler.",
                        recipeId = 1L
                    ),
                    ingredients = listOf(
                        IngredientWithQuantity(
                            id_recipe_part = 10L,
                            id_ingredient = 100L,
                            ingredient_label = "Farine",
                            id_quantity_type = 2L,
                            quantity_type_label = "grammes",
                            quantity = 250
                        ),
                        IngredientWithQuantity(
                            id_recipe_part = 10L,
                            id_ingredient = 101L,
                            ingredient_label = "Eau",
                            id_quantity_type = 2L,
                            quantity_type_label = "millilitres",
                            quantity = 150
                        ),
                        IngredientWithQuantity(
                            id_recipe_part = 10L,
                            id_ingredient = 102L,
                            ingredient_label = "Levure de boulanger",
                            id_quantity_type = 2L,
                            quantity_type_label = "grammes",
                            quantity = 5
                        ),
                        IngredientWithQuantity(
                            id_recipe_part = 10L,
                            id_ingredient = 103L,
                            ingredient_label = "Sel",
                            id_quantity_type = 2L,
                            quantity_type_label = "grammes",
                            quantity = 5
                        )
                    )
                ),
                RecipePartWithIngredients(
                    part = RecipePart(
                        id = 20L,
                        recipePartTitle = "Garniture",
                        recipePartProcess = "Étaler la sauce tomate, ajouter le fromage, puis garnir avec les feuilles de basilic.",
                        recipeId = 1L
                    ),
                    ingredients = listOf(
                        IngredientWithQuantity(
                            id_recipe_part = 20L,
                            id_ingredient = 104L,
                            ingredient_label = "Sauce tomate",
                            id_quantity_type = 2L,
                            quantity_type_label = "grammes",
                            quantity = 100
                        ),
                        IngredientWithQuantity(
                            id_recipe_part = 20L,
                            id_ingredient = 105L,
                            ingredient_label = "Mozzarella",
                            id_quantity_type = 2L,
                            quantity_type_label = "grammes",
                            quantity = 150
                        ),
                        IngredientWithQuantity(
                            id_recipe_part = 20L,
                            id_ingredient = 106L,
                            ingredient_label = "Basilic frais",
                            id_quantity_type = 1L,
                            quantity_type_label = "unité",
                            quantity = 5
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

    private val _recipe = MutableStateFlow<FullRecipe>(recipeItems.elementAt(0))  // Correct index 0
    val recipe: StateFlow<FullRecipe> get() = _recipe
}
