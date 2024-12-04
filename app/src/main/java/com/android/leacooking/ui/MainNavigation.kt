package com.android.leacooking.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.android.leacooking.ui.shared.topBar.TopBar
import com.android.leacooking.ui.home.HomeScreen
import com.android.leacooking.ui.recipe.RecipeScreen
import com.android.leacooking.ui.recipes.RecipesScreen
import com.android.leacooking.ui.subcategories.SubCategoriesScreen

enum class Screen(val route: String) {
    HOME("home"),
    SUBCATEGORIES("subcategories"),
    RECIPES("recipes"),
    RECIPE("recipe");

    override fun toString(): String {
        return route
    }
}

@Composable
fun MainNavigation() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.HOME.route) {
        composable(Screen.HOME.route) {
            Scaffold(
                topBar = { TopBar() },
            ) { innerPadding ->
                HomeScreen(
                    modifier = Modifier.padding(innerPadding).background(Color(251,194,181)),
                    navController = navController
                )
            }
        }
        composable(
            route = "${Screen.SUBCATEGORIES.route}/{categoryId}",
            arguments = listOf(navArgument("categoryId") { type = NavType.LongType })
        ) { backStackEntry ->
            val categoryId = backStackEntry.arguments?.getLong("categoryId") ?: 1L
            Scaffold(topBar = { TopBar() }) { innerPadding ->
                SubCategoriesScreen(
                    Modifier.padding(innerPadding).background(Color(251,194,181)),
                    categoryId = categoryId,
                    navController = navController
                )
            }
        }
        composable("${Screen.RECIPES.route}/{subCategoryId}",
            arguments = listOf(navArgument("subCategoryId") { type = NavType.LongType })
        ) { backStackEntry ->
            val subCategoryId = backStackEntry.arguments?.getLong("subCategoryId") ?: 1L
            Scaffold(topBar = { TopBar() }) { innerPadding ->
                RecipesScreen(
                    Modifier.padding(innerPadding).background(Color(251,194,181)),
                    subCategoryId = subCategoryId,
                    navController = navController
                )
            }
        }
        composable("${Screen.RECIPE.route}/{recipeId}",
            arguments = listOf(navArgument("recipeId") { type = NavType.LongType })
        ) { backStackEntry ->
            val recipeId = backStackEntry.arguments?.getLong("recipeId") ?: 1L
            Scaffold(topBar = { TopBar() }) { innerPadding ->
                RecipeScreen(
                    Modifier.padding(innerPadding),
                    recipeId = recipeId
                )
            }
        }
    }
}