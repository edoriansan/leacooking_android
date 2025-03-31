package com.android.leacooking.ui

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.android.leacooking.ui.shared.topBar.TopBar
import com.android.leacooking.ui.shared.topBar.SearchDialog
import com.android.leacooking.ui.home.HomeScreen
import com.android.leacooking.ui.recipe.RecipeScreen
import com.android.leacooking.ui.recipes.RecipesScreen
import com.android.leacooking.ui.recipes.search.RecipesSearchScreen
import com.android.leacooking.ui.subcategories.SubCategoriesScreen

enum class Screen(val route: String) {
    HOME("home"),
    SUBCATEGORIES("subcategories"),
    RECIPES("recipes"),
    RECIPE("recipe"),
    RECIPE_SEARCH("recipes_search");

    override fun toString(): String {
        return route
    }
}

@Composable
fun MainNavigation() {
    val navController = rememberNavController()
    var isSearchDialogOpen by remember { mutableStateOf(false) }
    val context = LocalContext.current

    fun handleSearch(query: String) {
        if (query.isEmpty()) {
            Toast.makeText(context, "Veuillez saisir quelque chose pour la recherche...", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Recherche pour : $query", Toast.LENGTH_SHORT).show()
            isSearchDialogOpen = false
            navController.navigate("recipes_search/$query")
        }
    }

    Scaffold(
        topBar = {
            TopBar(
                onSearchClick = { isSearchDialogOpen = true },
                onHomeClick = {
                    navController.navigate(Screen.HOME.route) {
                        popUpTo(Screen.HOME.route) { inclusive = true }
                    }
                },
                canGoBack = navController.previousBackStackEntry != null,
                onBackClick = { navController.popBackStack() }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.HOME.route,
            modifier = Modifier.padding(innerPadding).background(Color(251, 194, 181))
        ) {
            composable(Screen.HOME.route) {
                HomeScreen(navController = navController)
            }

            composable(
                route = "${Screen.SUBCATEGORIES.route}/{categoryId}",
                arguments = listOf(navArgument("categoryId") { type = NavType.LongType })
            ) { backStackEntry ->
                val categoryId = backStackEntry.arguments?.getLong("categoryId") ?: 1
                SubCategoriesScreen(categoryId = categoryId, navController = navController)
            }

            composable("${Screen.RECIPES.route}/{recipeSubcategoryId}",
                arguments = listOf(navArgument("recipeSubcategoryId") { type = NavType.LongType })
            ) { backStackEntry ->
                val recipeSubcategoryId = backStackEntry.arguments?.getLong("recipeSubcategoryId") ?: 1L
                RecipesScreen(recipeSubcategoryId = recipeSubcategoryId, navController = navController)
            }

            composable("${Screen.RECIPE.route}/{recipeId}",
                arguments = listOf(navArgument("recipeId") { type = NavType.LongType })
            ) { backStackEntry ->
                val recipeId = backStackEntry.arguments?.getLong("recipeId") ?: 1L
                RecipeScreen(recipeId = recipeId)
            }

            composable("${Screen.RECIPE_SEARCH.route}/{query}") { backStackEntry ->
                val query = backStackEntry.arguments?.getString("query") ?: ""
                RecipesSearchScreen(query = query, navController = navController)
            }
        }
    }

    if (isSearchDialogOpen) {
        SearchDialog(
            isOpen = isSearchDialogOpen,
            onDismiss = { isSearchDialogOpen = false },
            onSearch = { query -> handleSearch(query) }
        )
    }
}
