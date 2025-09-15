package com.android.leacooking.ui.recipes

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.android.leacooking.ui.Screen
import com.android.leacooking.ui.shared.imageCard.ImageCard

@Composable
fun RecipesScreen(
    modifier: Modifier = Modifier,
    recipeSubcategoryId: Long,
    navController: NavController,
    viewModel: RecipesViewModel = hiltViewModel()
) {
    LaunchedEffect(recipeSubcategoryId) {
        viewModel.loadRecipes(recipeSubcategoryId)
    }

    val recipes by viewModel.recipes.collectAsStateWithLifecycle()
    val isLandscape = isLandscape()
    val columns = if (isLandscape) 3 else 2

    BoxWithConstraints(
        modifier = modifier.fillMaxSize()
    ) {
        val cardSize: Dp = maxHeight / (if (isLandscape) 3 else 4)

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
        ) {
            items(recipes.chunked(columns)) { rowItems ->
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    rowItems.forEachIndexed { _, recipe ->
                        ImageCard(
                            imageUrl = recipe.imageUrl,
                            label = recipe.title,
                            modifier = Modifier
                                .height(cardSize)
                                .weight(1f)
                                .padding(5.dp)
                                .clickable {
                                    navController.navigate("${Screen.RECIPE.route}/${recipe.id}")
                                },
                        )
                    }

                    if (rowItems.size < columns) {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
        }
    }
}

@Composable
fun isLandscape(): Boolean {
    val configuration = androidx.compose.ui.platform.LocalConfiguration.current
    return configuration.orientation == android.content.res.Configuration.ORIENTATION_LANDSCAPE
}
