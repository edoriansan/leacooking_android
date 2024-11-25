package com.android.leacooking.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.android.leacooking.ui.shared.topBar.TopBar
import com.android.leacooking.ui.home.HomeScreen

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
                HomeScreen(modifier = Modifier.padding(innerPadding).background(Color(251,194,181)))
            }
        }
        /*composable(Screen.SUBCATEGORIES.route) {
            Scaffold(topBar = { TopBar() }) { innerPadding ->
                SubCategoriesScreen(
                    Modifier.padding(innerPadding),
                    navController = navController
                )
            }
        }
        composable(Screen.RECIPES.route) {
            Scaffold(topBar = { TopBar() }) { innerPadding ->
                RecipesScreen(
                    Modifier.padding(innerPadding),
                    navController = navController
                )
            }
        }
        composable(Screen.RECIPE.route) {
            Scaffold(topBar = { TopBar() }) { innerPadding ->
                RecipeScreen(
                    Modifier.padding(innerPadding),
                    navController = navController
                )
            }
        }*/
    }
}
